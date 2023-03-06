package quangson.bradley.pfit.jsf.myApp;

import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quangson.bradley.pfit.transaction.ejb.TrxManager;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named("cImport")
@ViewScoped
public class Import implements Serializable {

    private static final Logger logger = LogManager.getLogger(Import.class);

    @EJB
    private TrxManager trxManager;

    @Inject
    FacesContext facesContext;

    private Part uploadedFile;

    public void upload(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(uploadedFile.getInputStream()))){
            String headerRow = br.readLine(); //read first line which is the header row
            String line;
          while((line = br.readLine()) != null){
//              logger.info(line);
              String[] rows = line.split(",");
              // expected structure: Date,Source,Vendor,Amount,Notes
              var newTrx = trxManager.startTrxBuild()
//                      .date(LocalDate.parse(rows[0], DateTimeFormatter.ofPattern("M/d/yyyy")))
                      .date(Timestamp.valueOf(LocalDateTime.parse(rows[0], DateTimeFormatter.ofPattern("M/d/yyyy"))))
                      .source(rows[1])
                      .vendor(rows[2])
                      .amount(Double.parseDouble(rows[3]))
                      .notes(rows[4])
                      .owner(facesContext.getExternalContext().getUserPrincipal().getName())
                      .build();
//              logger.info(newTrx);
              trxManager.addTransaction(newTrx);
          }
        }
        catch (IOException ioe){
            // show faces message
        }
    }

    // getters and setters

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}
