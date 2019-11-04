package domini_exam.dominiGeiger_exam

import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

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
