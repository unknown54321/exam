package domini_exam.dominiGeiger_exam.dto

import domini_exam.dominiGeiger_exam.db.Geigerteller

object GeigertellerDtoConverter {

   fun transform(geigerteller: Geigerteller) : GeigertellerDto {

       return GeigertellerDto(
               deviceId = geigerteller.deviceId

       )
   }

    fun transform(geigerteller: Iterable<Geigerteller>) : List<GeigertellerDto>{

        return geigerteller.map { transform(it) }
    }
}
