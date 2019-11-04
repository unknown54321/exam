package domini_exam.dominiGeiger_exam

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import javax.persistence.*

@Entity
data class Geigerteller (


        @JsonManagedReference
        @OneToMany(mappedBy = "device", fetch = FetchType.EAGER)
        @ElementCollection
        var measurement: List<GeigetellerMeasurement> = emptyList(),

        @Id
        @GeneratedValue
        var deviceId: Long? = null


)
