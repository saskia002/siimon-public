package siimon.core.api.module.avaleht.util;

public class EmailTemplate {

	public static final String AVAlEHT_COURSE_REG_EMAIL_TEMPLATE = """
		<!DOCTYPE html>
	  <html>
		  <head>
			<title>Uus registreerumine</title>
			<style>
				table, th, td {
					border: 1px solid black;
					border-collapse: collapse;
				}
				th {
					width: 8rem;
					padding: 0.5rem;
				}
				td {
					width: max-content;
					min-width: 8rem;
					padding: 0.5rem;
				}
			</style>
		  </head>
		  <body>
				<h3>Uus registreerumine:</h3>
					<table>
						<tr>
							<th align='left'>Nimi</th>
							<td>%s</td>
						</tr>
						<tr>
							<th align='left'>Meil</th>
							<td>%s</td>
						</tr>
						<tr>
							<th align='left'>Telefon</th>
							<td>%s</td>
						</tr>
						<tr>
							<th align='left'>ID kood</th>
							<td>%s</td>
						</tr>
						<tr>
							<th align='left'>Kursuse nimi</th>
							<td>%s</td>
						</tr>
						<tr>
							<th align='left'>Kursuse kuupäev</th>
							<td>%s</td>
						</tr>
					</table>
		  </body>
	  </html>
	""";

	public static String genRegistrationEmailTemplate(
			String fullName, String mail, String phone, String idCode, String courseName, String courseDate
	) {
		return String.format(AVAlEHT_COURSE_REG_EMAIL_TEMPLATE, fullName, mail, phone, idCode, courseName, courseDate);
	}

}
