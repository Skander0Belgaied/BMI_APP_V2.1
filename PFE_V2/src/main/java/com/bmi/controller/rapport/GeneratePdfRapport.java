package com.bmi.controller.rapport;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bmi.app.entity.Etat;
import com.bmi.app.entity.Filter;
import com.bmi.app.repository.EtatRepository;
import com.bmi.app.repository.FilterRepository;
import com.bmi.app.repository.RapportRepository;
import com.bmi.rapport.entity.Bmiequ;
import com.bmi.rapport.repository.BmiequRepository;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class GeneratePdfRapport {
	@Autowired
	BmiequRepository bmiequRepository;
	@Autowired
	ApplicationContext context;

	/*
	 * // @GetMapping(path = "pdf/{jrxml}")
	 * 
	 * @GetMapping(path = "/rapports/pdf")
	 * 
	 * @ResponseBody // public void getPdf(@PathVariable String jrxml,
	 * HttpServletResponse response) throws Exception { public void
	 * getPdf(HttpServletResponse response) throws Exception { //Get JRXML template
	 * from resources folder // Resource resource =
	 * context.getResource("classpath:reports/" + jrxml + ".jrxml"); Resource
	 * resource = context.getResource("classpath:reports/EQU.jrxml"); //Compile to
	 * jasperReport InputStream inputStream = resource.getInputStream();
	 * JasperReport report = JasperCompileManager.compileReport(inputStream);
	 * 
	 * //Parameters Set Map<String, Object> params = new HashMap<>();
	 * 
	 * List<Bmiequ> cars = (List<Bmiequ>) bmiequRepository.findAll();
	 * 
	 * //Data source Set JRDataSource dataSource = new
	 * JRBeanCollectionDataSource(cars,true); params.put("datasource", dataSource);
	 * 
	 * //Make jasperPrint JasperPrint jasperPrint =
	 * JasperFillManager.fillReport(report, null, dataSource); //Media Type
	 * response.setContentType(MediaType.APPLICATION_PDF_VALUE); //Export PDF Stream
	 * JasperExportManager.exportReportToPdfStream(jasperPrint,
	 * response.getOutputStream()); }
	 */

//    @GetMapping(path = "pdf/{jrxml}")
	@GetMapping(path = "/rapports/pdf")
	@ResponseBody
//	    public void getPdf(@PathVariable String jrxml, HttpServletResponse response) throws Exception {
	public void getPdf(HttpServletResponse response) throws Exception {
		// Get JRXML template from resources folder
//	        Resource resource = context.getResource("classpath:reports/" + jrxml + ".jrxml");
		Resource resource = context.getResource("classpath:reports/EQU.jrxml");
		// Compile to jasperReport
		InputStream inputStream = resource.getInputStream();
		JasperReport report = JasperCompileManager.compileReport(inputStream);
		double total = bmiequRepository.findbyTotal();
		// Parameters Set
		Map<String, Object> params = new HashMap<>();
		System.out.println(total);
		params.put("Total", total);
		List<Bmiequ> cars = (List<Bmiequ>) bmiequRepository.findAll();
		// Data source Set

		JRDataSource dataSource = new JRBeanCollectionDataSource(cars);
		// System.out.println(dataSource.));

		// Make jasperPrint
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
		// Media Type
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		// Export PDF Stream
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}

	/******************************************
	****** With JDBC Connection Exemple *******
	*******************************************/

	Statement stmt = null;
	ResultSet resultset = null;
	ResultSet resultset1 = null;
	Connection con = null;
	Connection con1 = null;
	int unitId = 1;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String connectionurl = "jdbc:sqlserver://pfe2019.hopto.org;databaseName=MAINTA_TEST?user=sa&password=Admin123";
	String connection = "jdbc:sqlserver://pfe2019.hopto.org:1433;"
			+ "databaseName=MAINTA_TEST;user=sa;password=Admin123;";
	List<Object> shortnamelist = new ArrayList<Object>();

	@GetMapping(path = "/rapports/rpdf")
	@ResponseBody

	public void getrPdf(HttpServletResponse response) throws Exception {

		response.setContentType( "application/pdf" );
		/*response.setHeader("Content-disposition",
                "attachment; filename=" +
                "Example.pdf" );*/
		try {
			String selectstatement = "select * from VPER_BMIEQU where ID_NUMBT_BT=92365 ";
			Class.forName(driver);
			con1 = DriverManager.getConnection(connection);
			stmt = con1.createStatement();
			resultset = stmt.executeQuery(selectstatement);
			JRResultSetDataSource resultsetdatasource = new JRResultSetDataSource(resultset);

			String selectstatement1 = "select dbo.totale()";
			Class.forName(driver);
			con = DriverManager.getConnection(connection);
			stmt = con.createStatement();
			resultset1 = stmt.executeQuery(selectstatement1);
			resultset1.next();
			double total = resultset1.getDouble(1);

			/*
			 * while (resultset.next()) { double shortname =
			 * resultset.getDouble("Total_cout"); System.out.println("Total_cout" +
			 * shortname); double workamount =s; System.out.println("Total" + workamount);
			 * shortnamelist.add(shortname); shortnamelist.add(workamount); }
			 */

			// System.out.println("shortnamelist" + shortnamelist.size());

			Map<String, Object> hashmap = new HashMap<String, Object>();
			hashmap.put("Total", total);
			// String realpath =
			// FacesContext.getCurrentInstance().getExternalContext().getRealPath("common/reports/registerofwages.jasper");
			Resource resource = context.getResource("classpath:reports/EQU2.jrxml");
			InputStream inputStream = resource.getInputStream();
			JasperReport report = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperprint = JasperFillManager.fillReport(report, hashmap, con1);

			try {
				/*
				 * final Exporter exporter;//begin html export exporter = new HtmlExporter();
				 * exporter.setExporterOutput(new
				 * SimpleHtmlExporterOutput(response.getOutputStream()));
				 * exporter.setExporterInput(new SimpleExporterInput(jasperprint));
				 * exporter.exportReport();//end hml export
				 */
				JasperExportManager.exportReportToPdfStream(jasperprint, response.getOutputStream());
			} catch (JRException ex) {
				ex.getMessage();
			}
		} catch (net.sf.jasperreports.engine.JRException JRexception) {
			System.out.println("JRException Exception" + JRexception.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close(stmt);
			// close(resultset);
			// close(con);
		}
	}
	/******************************************
	****** With JDBC Connection  Works ********
	*******************************************/
	@Autowired
	FilterRepository filterRepository;
	@Autowired
	RapportRepository rapportRepository;
	@Autowired
	EtatRepository etatRepository;
	@GetMapping(path = "/GenerateRapport-pdf" )
	@ResponseBody
	void /* String*/ generateRapport(@RequestParam MultiValueMap<String, String> queryMap,HttpServletResponse response,HttpServletRequest request)throws Exception {
		response.setContentType( "application/pdf" );
		Map<String, Object> hashmap = new HashMap<String, Object>();
		
		/***************find all filters of rapport X ******************/
		List<Filter> filtersList = new ArrayList<Filter>();
		List<Etat> etatsFilters = etatRepository.findByEtatIdRapportId(Long.parseLong(queryMap.get("rapportId").get(0)));
		for (Etat etat : etatsFilters) {
			filtersList.add(filterRepository.findByFilterId(etat.getEtatId().getFilterId()));
		}
		/******************************************************************/
		String where="where 1=1 AND ";
		String operatorLogique=" AND ";
		int i=0;
		if(request.getQueryString().contains("input-filters")){
			List<String> itemIds=queryMap.get("input-filters");}
		for(Filter filter:filtersList) {
			
			if(queryMap.containsKey("input-filters"))
			{	
				if(queryMap.get("input-filters").contains(String.valueOf(filter.getFilterId()))) {
					
					if(queryMap.containsKey("operatorLogique_"+filter.getFilterChamp())) {
						
						if((queryMap.get("operatorLogique_"+filter.getFilterChamp()).get(0)).equals("OR")) {
						operatorLogique=" OR ";
						}else
						{
							operatorLogique=" AND ";
						}
					}
					else
					{
						operatorLogique="";
					}
				int numberOfFilterX=queryMap.get(queryMap.get("input-filters").get(i)).size();
				String filtersValue="";
				for(int j=0;j<numberOfFilterX;j++) {
					filtersValue+=queryMap.get(queryMap.get("input-filters").get(i)).get(j);
					
if(filter.getFilterType().equals("date")) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    String Date=sdf.format(sdf2.parse(queryMap.get(queryMap.get("input-filters").get(i)).get(j)));
						if(j==0) 
						{hashmap.put("DateDebut",Date);}
						else
						{hashmap.put("DateFin",Date);}
					}
					else if(j+1<numberOfFilterX) {
						filtersValue+=" , ";
					}
						
				}
				if(filter.getFilterType()!="date") 
				{hashmap.put(filter.getFilterChamp(),filtersValue);}
for(int j=0;j<numberOfFilterX;j++) {
	if(queryMap.containsKey("operator_"+filter.getFilterChamp())) {
				if((queryMap.get("operator_"+filter.getFilterChamp()).get(0)).equals("like%")) {
					where+=filter.getFilterChamp()+" LIKE '"+queryMap.get(queryMap.get("input-filters").get(i)).get(j)+"%'";
					}
				else if((queryMap.get("operator_"+filter.getFilterChamp()).get(0)).equals("%like%")) {
					where+=filter.getFilterChamp()+" LIKE '%"+queryMap.get(queryMap.get("input-filters").get(i)).get(j)+"%'";
					}
				else if((queryMap.get("operator_"+filter.getFilterChamp()).get(0)).equals("%like")) {
					where+=filter.getFilterChamp()+" LIKE '%"+queryMap.get(queryMap.get("input-filters").get(i)).get(j)+"'";
					}else
					{
						where+=filter.getFilterChamp()+" ='"+queryMap.get(queryMap.get("input-filters").get(i)).get(j)+"'";
					}
					}else
					{
						if(filter.getFilterType().equals("date")) {
							if(j==0)
							{
								where+=filter.getFilterChamp()+" BETWEEN ";
							}
			                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			                String Date=sdf.format(sdf2.parse(queryMap.get(queryMap.get("input-filters").get(i)).get(j)));
			               where+=" '"+Date+"' ";
					
					}else {
					
					where+=filter.getFilterChamp()+" ='"+queryMap.get(queryMap.get("input-filters").get(i)).get(j)+"'";
					}
						
					}
	
				if(j+1<numberOfFilterX) {
					if(filter.getFilterType().equals("date")) {
						where+=" AND ";
					}
					else
					{where+=" OR ";}
				}
}
				if(queryMap.get("input-filters").size()!=i ) {
					
					where+=operatorLogique;
			
				}
				i++;
			}else
			{		if(filter.getFilterType()!="date") {
				hashmap.put(filter.getFilterChamp(),"ALL");
			}
			}

			}
			else 
			{
				where="where 1=1";
				if(filter.getFilterType()!="date") {
				hashmap.put(filter.getFilterChamp(),"ALL");
				}
			}
			
		}
		
		
		hashmap.put("where", where);

		String RapportUrl=rapportRepository.findByRapportId(Long.parseLong(queryMap.get("rapportId").get(0))).getRapportUrl();

		 try {
			 
			 
				String selectstatement = "select * from VPER_BMIEQU where Total_cout<>0";
				Class.forName(driver);
				con1 = DriverManager.getConnection(connection);
				stmt = con1.createStatement();
				resultset = stmt.executeQuery(selectstatement);
				JRResultSetDataSource resultsetdatasource = new JRResultSetDataSource(resultset);

				String selectstatement1 = "select dbo.totale()";
				Class.forName(driver);
				con = DriverManager.getConnection(connection);
				stmt = con.createStatement();
				resultset1 = stmt.executeQuery(selectstatement1);
				resultset1.next();
				double total = resultset1.getDouble(1);
				
				Resource resource = context.getResource("classpath:reports/"+RapportUrl+".jrxml");
				InputStream inputStream = resource.getInputStream();
				JasperReport report = JasperCompileManager.compileReport(inputStream);
				JasperPrint jasperprint = JasperFillManager.fillReport(report, hashmap, con1);

				try {
					JasperExportManager.exportReportToPdfStream(jasperprint, response.getOutputStream());
				} catch (JRException ex) {
					ex.getMessage();
				}
			} catch (net.sf.jasperreports.engine.JRException JRexception) {
				System.out.println("JRException Exception" + JRexception.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// close(stmt);
				// close(resultset);
				// close(con);
			}
	
	}

}
