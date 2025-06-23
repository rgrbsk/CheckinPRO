package systemReports;
import net.sf.jasperreports.engine.JasperCompileManager;

public class Compiler {
    public static void main(String[] args) {
        try {
            String jrxmlFile = "src/systemReports/ReciboPdf.jrxml";
            String jasperFile = "src/systemReports/ReciboPdf.jasper";

            JasperCompileManager.compileReportToFile(jrxmlFile, jasperFile);
            System.out.println("Relatório compilado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
