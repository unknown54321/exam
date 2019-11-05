package domini_exam.dominiGeiger_exam

import domini_exam.dominiGeiger_exam.db.Geigerteller
import domini_exam.dominiGeiger_exam.dto.GeigertellerDtoConverter
import domini_exam.dominiGeiger_exam.dto.GeigertellerDto
import domini_exam.dominiGeiger_exam.dto.common.RestResponseFactory
import domini_exam.dominiGeiger_exam.dto.common.WrappedResponse
import domini_exam.dominiGeiger_exam.repository.GeigertellerRepository
import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping(path = ["/devices"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GeigertellerController(
        @Autowired private val geigerRepository: GeigertellerRepository,
        @Autowired private val meterRegistry: MeterRegistry

) {

    private val logger = LoggerFactory.getLogger(GeigertellerController::class.java)


    // Get all the devices
    @GetMapping
    fun getAll(

    ): ResponseEntity<WrappedResponse<MutableIterable<Geigerteller>>> {

        val devices = geigerRepository.findAll()

        logger.info("These are the devices that has returned {} ", devices.count())
        return RestResponseFactory.payload(200, devices)
    }

    //Find the specific id of the device
    @GetMapping(path = ["/{deviceId}"])
    fun getByDeviceId(
            @PathVariable("deviceId")
            pathId: Long
    ): ResponseEntity<WrappedResponse<GeigertellerDto>> {

        val deviceId: Long
        try {
            deviceId = pathId.toLong()
        } catch (e: Exception) {
            return RestResponseFactory.userFailure("Invalid deviceId")
        }

    val deviceName = geigerRepository.findById(deviceId).orElse(null)
            ?: return RestResponseFactory.notFound("Geigerteller with $deviceId does not exist")
        logger.info("Device has been found")
        return RestResponseFactory.payload(200, GeigertellerDtoConverter.transform(deviceName))

    }


    // create a device
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Timed
    fun createDevice(
            @RequestBody
            dtomeasurement: GeigertellerDto
    ): ResponseEntity<Geigerteller> {

        val device = geigerRepository.save(Geigerteller())
        logger.info("Device has been created with the id of {}", device.deviceId)

        return ResponseEntity.ok(device)
    }

    //delete a device
    @DeleteMapping(path = ["/{deviceId}"])
    fun deleteDeviceById(
            @PathVariable("deviceId")
            pathId: String
    ): ResponseEntity<WrappedResponse<Void>> {

        val id: Long
        try {
            id = pathId.toLong()
        } catch (e: Exception) {
            return RestResponseFactory.userFailure("Invalid id")
        }

        if (!geigerRepository.existsById(id)) {
            return RestResponseFactory.notFound("Device with id $id does not exist")
        }

        geigerRepository.deleteById(id)
        logger.info("Device $id has been deleted")

        return RestResponseFactory.noPayload(204)
    }



}
