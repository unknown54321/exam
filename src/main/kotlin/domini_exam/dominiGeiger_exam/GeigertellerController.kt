package domini_exam.dominiGeiger_exam

import domini_exam.dominiGeiger_exam.dto.GeigertellerDto
import domini_exam.dominiGeiger_exam.dto.RestResponseFactory
import domini_exam.dominiGeiger_exam.dto.WrappedResponse
import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.DistributionSummary
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
    private val counterCreated = Counter.builder("counter.created")
            .description("Measurements has been created")
    private val counterHasNotBeenFound = Counter.builder("counter.NotBeenfound")
            .description("Measurements data is invalid")
    private val sievertDSummary = DistributionSummary
            .builder("distribution.sievert")
            .description("Distribution of Sievert")
            .baseUnit("sievert")
            // percentile values in the app, median and 95th percentiles
            .publishPercentiles(0.5, 0.95)
            .register(meterRegistry)


    // Get all the devices
    @GetMapping
  //  fun all(): MutableIterable<Geigerteller> = this.geigerRepository.findAll()
    fun getAll(

    ): ResponseEntity<WrappedResponse<MutableIterable<Geigerteller>>> {
        val devices = geigerRepository.findAll()
        logger.debug("Task has been running ")

        logger.info("These are the devices that has returned {} ", devices.count())
        return RestResponseFactory.payload(200, devices)
    }

    //Find the spesific id of the device
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

        return RestResponseFactory.payload(200, DtoConverter.transform(deviceName))

    }



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
        logger.info("Device $id has been deleted", counterCreated)

        return RestResponseFactory.noPayload(204)
    }

    /*
    @PostMapping("/{deviceId}/measurements")
    @Timed
    fun createMeasurements(
            @PathVariable("device")
    )
*/


}
