package siimon.core.api.module.avaleht.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siimon.core.api.module.avaleht.dto.CourseDto;
import siimon.core.api.module.avaleht.dto.CourseRegDto;
import siimon.core.api.module.avaleht.dto.courseReg.AddCourseRegDto;
import siimon.core.api.module.avaleht.mapper.AddCourseRegMapper;
import siimon.core.api.module.avaleht.mapper.CourseMapper;
import siimon.core.api.module.avaleht.mapper.CourseRegMapper;
import siimon.core.api.module.avaleht.repository.PersonRepository;
import siimon.core.api.module.avaleht.repository.CourseRegRepository;
import siimon.core.api.module.avaleht.repository.CourseRepository;
import siimon.core.api.module.avaleht.util.EmailTemplate;
import siimon.core.api.shared.EmailService;
import siimon.core.api.shared.dto.RespDto;
import siimon.core.api.shared.exception.NotFoundException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {

	private final CourseRepository courseRepository;
	private final CourseRegRepository courseRegRepository;
	private final PersonRepository PersonRepository;
	private final EmailService emailService;


	@Transactional(readOnly = true)
	public List<CourseDto> getAllCourses() {
		List<CourseDto> courses = CourseMapper.mapToDtos(courseRepository.findAll());
		if (courses.isEmpty()) {
			throw new NotFoundException("Could not find course(s)", "courseNotFound");
		}
		return courses;
	}

	@Transactional
	public RespDto addCourse(CourseDto courseDto) {
		courseRepository.save(CourseMapper.mapToModel(courseDto));
		return new RespDto("deleteCourse", "Course deletion successful", "/v2/course");
	}

	@Transactional
	public RespDto deleteCourse(Integer id) {
		if (!courseRepository.existsById(id)) {
			throw new NotFoundException("Cannot find course to delete", "courseNotFound");
		}
		courseRepository.deleteById(id);
		return new RespDto("deleteCourse", "Course deletion successful","/v2/course");
	}


	@Transactional(readOnly = true)
	public List<CourseRegDto> getAllCourseRegistrations() {
		List<CourseRegDto> courseRegs = CourseRegMapper.mapToDtos(courseRegRepository.findAll());
		if (courseRegs.isEmpty()) {
			throw new NotFoundException("No course registrations found", "courseNotFound");
		}
		return courseRegs;
	}

	@Transactional
	public RespDto registerCourse(AddCourseRegDto addCourseRegDto) {
		var course = courseRepository.findById(addCourseRegDto.getCourseId())
				.orElseThrow(() -> new NotFoundException("Course not found", "courseNotFound"));
		var person = PersonRepository.save(Objects.requireNonNull(AddCourseRegMapper.mapToPersonModel(addCourseRegDto.getPerson())));
		var reg = courseRegRepository.save(AddCourseRegMapper.mapToRegModel(addCourseRegDto, person.getId()));

		emailService.sendHtmlEmail(
				"?????????@????????.ee",
				"Kursuse registreerimine",
				EmailTemplate.genRegistrationEmailTemplate(
						addCourseRegDto.getPerson().getFullName(),
						addCourseRegDto.getPerson().getMail(),
						addCourseRegDto.getPerson().getPhone(),
						addCourseRegDto.getPerson().getIdCode(),
						course.getTitle(),
						course.getDate()
				)
		);

		return new RespDto("addNewCourseReg", "Course registration successful", "/v2/course");
	}

}
