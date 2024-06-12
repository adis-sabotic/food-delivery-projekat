package service;


import model.Korisnik;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.ws.rs.core.MediaType;



public class MultipartRequest {
    @RestForm("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    @Schema(implementation = MultipartResource.UploadSchema.class)
    private FileUpload file;

    @RestForm("korisnik")
    @PartType(MediaType.APPLICATION_JSON)
    private Korisnik korisnik;

    public FileUpload getFile() {
        return file;
    }

    public void setFile(FileUpload file) {
        this.file = file;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }


}