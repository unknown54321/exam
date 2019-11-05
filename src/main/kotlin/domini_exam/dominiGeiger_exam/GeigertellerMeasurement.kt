package domini_exam.dominiGeiger_exam.db

import com.fasterxml.jackson.annotation.JsonBackReference
import domini_exam.dominiGeiger_exam.db.Geigerteller
import javax.persistence.*

@Entity
data class GeigetellerMeasurement (

        var lat: Float? = null,
        var long: Float? = null,
        var sievert: Int? = null,

        @JsonBackReference
        @ManyToOne
        @JoinColumn(name="device_deviceId", nullable = false)
        var device: Geigerteller? = null,

        @Id
        @GeneratedValue
        var deviceId: Long? = null

)
