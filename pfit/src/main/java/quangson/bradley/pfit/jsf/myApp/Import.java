package quangson.bradley.pfit.jsf.myApp;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Paths;

@Named("cImport")
@ViewScoped
public class Import implements Serializable {

    private Part uploadedFile;
    private String filename;
    private byte[] fileContent;

    public void upload(){
        filename = Paths.get(uploadedFile.getSubmittedFileName())
                .getFileName()
                .toString();
        try (InputStream input = uploadedFile.getInputStream()){
            fileContent = input.readAllBytes();
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
