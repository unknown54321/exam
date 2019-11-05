package domini_exam.dominiGeiger_exam.repository

import domini_exam.dominiGeiger_exam.db.Geigerteller
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface GeigertellerRepository : CrudRepository<Geigerteller, Long>
