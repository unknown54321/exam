package domini_exam.dominiGeiger_exam

import domini_exam.dominiGeiger_exam.Geigerteller
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface GeigertellerRepository : CrudRepository<Geigerteller, Long>
