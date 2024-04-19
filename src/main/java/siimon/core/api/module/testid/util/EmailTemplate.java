package siimon.core.api.module.testid.util;

public class EmailTemplate {

	public static final String TESTID_RESULT_EMAIL_TEMPLATE = """
		<!DOCTYPE html>
			<html>
			    <head>
				    <title>Uus testitulemus</title>
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
					<h3>Uus testitulemus:</h3>
						<table>
							<tr>
								<th align='left'>Nimi</th>
								<td>%s</td>
							</tr>
							<tr>
								<th align='left'>Keeletase</th>
								<td>%s</td>
							</tr>
							<tr>
								<th align='left'>Tulemus (%%)</th>
								<td>%d %%</td>
							</tr>
						</table>
			  </body>
		  </html>
	""";

	public static String genTestidEmailTemplate(String fullName, String langLevel, Integer resultPercentage) {
		return String.format(TESTID_RESULT_EMAIL_TEMPLATE, fullName, langLevel, resultPercentage);
	}

}
