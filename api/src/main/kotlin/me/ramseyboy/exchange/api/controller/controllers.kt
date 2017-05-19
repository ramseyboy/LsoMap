package me.ramseyboy.exchange.api.controller

import me.ramseyboy.exchange.api.domain.AreaCode
import me.ramseyboy.exchange.api.domain.AreaCodeRepository
import me.ramseyboy.exchange.api.domain.Switch
import me.ramseyboy.exchange.api.domain.SwitchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class IndexController {

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/")
    fun index() = "/"
}

@RestController
class SwitchController @Autowired constructor(private val switchRepository: SwitchRepository) {

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/switch")
    fun allSwitches(page: Pageable) = switchRepository.findAll<Sort>(page)

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/switch/{id}")
    fun switchById(@PathVariable id: Int?): ResponseEntity<Switch> {
        val maybeSwitch = switchRepository.findById(id)
        if (maybeSwitch.isPresent) {
            val found = maybeSwitch.get()
            return ResponseEntity.ok(found)
        }
        return ResponseEntity.notFound().build<Switch>()
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/switch", params = arrayOf("region"))
    fun switchByRegion(page: Pageable, @RequestParam("region") region: String) = switchRepository.findByRegion(page, region)

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/switch", params = arrayOf("npa_nxx"))
    fun switchByNpaNxx(page: Pageable, @RequestParam("npa_nxx") npaNxx: String) = switchRepository.findByNpaNxx(page, npaNxx)

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/switch", params = arrayOf("area_code"))
    fun switchByAreaCode(page: Pageable, @RequestParam("area_code") areaCode: String) = switchRepository.findByAreaCode(page, areaCode)

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/switch", params = arrayOf("exchange"))
    fun switchByExchange(page: Pageable, @RequestParam("exchange") exchange: String) = switchRepository.findByExchange(page, exchange)

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/switch", params = arrayOf("switch_id"))
    fun switchBySwitchId(page: Pageable, @RequestParam("switch_id") switchId: String) = switchRepository.findBySwitchId(page, switchId)
}

@RestController
class AreaCodeController @Autowired constructor(private val areaCodeRepository: AreaCodeRepository) {

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/areacode")
    fun allAreaCodes(page: Pageable): Page<AreaCode> = areaCodeRepository.findAll<Sort>(page)

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/areacode/{id}")
    fun areaCodeById(@PathVariable id: Int): ResponseEntity<AreaCode> {
        val areaCode = areaCodeRepository.findById(id)
        return areaCode
                .map {
                    ResponseEntity.ok(it)
                }
                .orElseGet {
                    ResponseEntity.notFound().build()
                }
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/areacode", params = arrayOf("state"))
    fun areaCodeByState(page: Pageable, @RequestParam("state") state: String) = areaCodeRepository.findByState(page, state)

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/areacode", params = arrayOf("area_code"))
    fun areaCodeByNpa(page: Pageable, @RequestParam("area_code") areaCode: String) = areaCodeRepository.findByNpa(page, areaCode)
}


