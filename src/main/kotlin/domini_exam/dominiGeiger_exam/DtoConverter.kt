package domini_exam.dominiGeiger_exam

import domini_exam.dominiGeiger_exam.dto.GeigertellerDto

object DtoConverter {

   fun transform(geigerteller: Geigerteller) : GeigertellerDto {

       return GeigertellerDto(
               deviceId = geigerteller.deviceId

       )
   }

    fun transform(geigerteller: Iterable<Geigerteller>) : List<GeigertellerDto>{

        return geigerteller.map { transform(it) }
    }
}
