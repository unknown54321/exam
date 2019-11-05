package domini_exam.dominiGeiger_exam.db

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "Geigerteller")
data class Geigerteller (


        @JsonManagedReference
        @OneToMany(mappedBy = "device", fetch = FetchType.EAGER)
        @ElementCollection
        var measurement: List<GeigetellerMeasurement> = emptyList(),

        @Id
        @GeneratedValue
        var deviceId: Long? = null


)
