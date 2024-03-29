package quangson.bradley.pfit.jsf.myApp;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quangson.bradley.pfit.transaction.Transaction;
import quangson.bradley.pfit.transaction.ejb.TrxManager;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named("cImport")
@ViewScoped
public class Import implements Serializable {

    private static final Logger logger = LogManager.getLogger(Import.class);

    @EJB
    private TrxManager trxManager;

    @Inject
    FacesContext facesContext;

    private String jsfOwner;

    @PostConstruct
    private void init(){
        jsfOwner = facesContext.getExternalContext().getUserPrincipal().getName();
    }
    private Part uploadedFile;

    public void upload(){
//        String filename = Paths.get(uploadedFile.getSubmittedFileName())
//                .getFileName()
//                .toString();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(uploadedFile.getInputStream()))){
            String headerRow = br.readLine(); //read first line which is the header row
            String line;
            int rowCount = 1;
            while((line = br.readLine()) != null){
              String[] rows = line.split("\\|\\|");
              // expected structure: Date,Source,Vendor,Amount,Notes
              var newTrx = buildTrx(rows);
              trxManager.addTransaction(newTrx);
              rowCount++;
          }
        }
        catch (IOException ioe){
            // show faces message
        }
    }

    private Transaction buildTrx(String[] rows) {
        return trxManager.startTrxBuild()
                .date(LocalDate.parse(rows[0], DateTimeFormatter.ofPattern("M/d/yyyy")))
                .source(rows[1])
                .vendor(rows[2])
                .amount(Double.parseDouble(rows[3]))
                .notes(rows.length > 4 ? rows[4] : "")
                .owner(jsfOwner)
                .build();
    }


    // getters and setters

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}
