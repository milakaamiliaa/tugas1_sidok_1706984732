package apap.tugas.sidok.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="spesialisasiDokter")
public class SpesialisasiDokterModel {

    @Id
    @Max(20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="id_dokter", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DokterModel dokter;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="id_spesialisasi", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SpesialisasiModel spesialisasi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DokterModel getDokter() {
        return dokter;
    }

    public void setDokter(DokterModel dokter) {
        this.dokter = dokter;
    }

    public SpesialisasiModel getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(SpesialisasiModel spesialisasi) {
        this.spesialisasi = spesialisasi;
    }
}
