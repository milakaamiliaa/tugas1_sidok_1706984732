package apap.tugas.sidok.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="spesialisasi")
public class SpesialisasiModel {

    @Size(max=20)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max=255)
    @Column(name="gelar", nullable = false)
    private String gelar;

    @OneToMany(mappedBy = "spesialisasi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<SpesialisasiDokterModel> listSpesialisasiDokter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }
}
