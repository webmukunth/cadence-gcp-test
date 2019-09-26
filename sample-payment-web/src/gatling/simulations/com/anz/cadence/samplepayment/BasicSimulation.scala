package com.anz.cadence.samplepayment

import java.time.{LocalDateTime, ZoneId}
import java.util.UUID.randomUUID

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  private val bodyTemplate = StringBody(
    """<?xml version="1.0" encoding="UTF-8"?>
      |<ns0:PmtAddRq xmlns:ns0="http://schema.cmo.anz/PmtAdd" id="${id}">
      |  <RqUID>${rqUID}</RqUID>
      |  <MsgHdr>
      |    <ClientDt>2018-11-20T08:44:29.934Z</ClientDt>
      |    <ClientName>TGO</ClientName>
      |    <PartyId>DRHCUST1</PartyId>
      |    <Version>1.0</Version>
      |  </MsgHdr>
      |  <Security>
      |    <TerminalId>201.3.155.170</TerminalId>
      |    <AuthenticationMethd>G</AuthenticationMethd>
      |  </Security>
      |  <PayHdr>
      |    <PaymentID>20000114560</PaymentID>
      |    <PaymentTRN>OPEAU20000114560</PaymentTRN>
      |    <ProcDate>2018-11-20</ProcDate>
      |    <AcctTranType>FT</AcctTranType>
      |  </PayHdr>
      |  <FromFIData>
      |    <Country>AU</Country>
      |    <BIC>ANZBAU3MXXX</BIC>
      |  </FromFIData>
      |  <FromCust>
      |    <Name>*EXPOSITO PRIVATE ACTION GENERAL</Name>
      |    <Addr1>66 BRADLEY ST</Addr1>
      |    <BRNorCRN>DRHCUST1-2</BRNorCRN>
      |    <LastAuthoriser>SHAMITHA1</LastAuthoriser>
      |    <ARN>DRHCUST1</ARN>
      |    <Reference>testtest</Reference>
      |  </FromCust>
      |  <FromAcct>
      |    <AcctId>841677913</AcctId>
      |    <AcctSys>CMM</AcctSys>
      |    <Branch>012003</Branch>
      |    <Name>*EXPOSITO PRIVATE ACTION GENERAL</Name>
      |    <PmtAuthMethod>AFPONLY</PmtAuthMethod>
      |    <AutoException>N</AutoException>
      |    <Narrative>114560 TO 013408 9703378testtest</Narrative>
      |    <CurCode>AUD</CurCode>
      |    <Amount>51.00</Amount>
      |    <AcctUse>BUSINESS</AcctUse>
      |  </FromAcct>
      |  <ToFIData>
      |    <Country>AU</Country>
      |    <BIC>ANZBAU3MXXX</BIC>
      |    <Branch>013408</Branch>
      |    <Name>Reservoir</Name>
      |    <Addr1>2 WILLIAMGOOD CT</Addr1>
      |  </ToFIData>
      |  <Clearing>
      |    <ClearPref>BKT</ClearPref>
      |    <Date>2018-11-20</Date>
      |  </Clearing>
      |  <ToBene>
      |    <Name>*VACIC UNITED TRANSPORT GENERAL</Name>
      |    <Addr1>2 WILLIAMGOOD CT</Addr1>
      |  </ToBene>
      |  <ToAcct>
      |    <AcctId>9703378</AcctId>
      |    <AcctSys>CMM</AcctSys>
      |    <Branch>013408</Branch>
      |    <CurCode>AUD</CurCode>
      |    <Amount>51.00</Amount>
      |    <Narrative>114560 FROM 012003841677913testtest</Narrative>
      |    <OwnAccount>Y</OwnAccount>
      |    <AcctUse>BUSINESS</AcctUse>
      |  </ToAcct>
      |</ns0:PmtAddRq>""".stripMargin
  )

  private val httpProtocol = http
    .baseUrl("http://sample-payment-web:8080")
    .contentTypeHeader("application/vnd.gpa.v1+xml")
    .acceptHeader("application/vnd.wf-res.v1+json")
    .userAgentHeader("Gatling/" + this.getClass.getCanonicalName)

  /* sample payment request */
  private val samplePayment = http("submitPayment")
    .post("/submitPayment")
    .body(bodyTemplate)
    .check(
      status.is(200),
      headerRegex(HttpHeaderNames.ContentType, "application/vnd.wf-res.v1\\+json"),
      jsonPath("$[?(@.workflowId == '${id}')]").exists,
      responseTimeInMillis.lt(1000)
    )

  /* sample payment scenario */
  private val scn = scenario("Basic Simulation")
    .exec { session =>
      session
        .set("id", randomUUID.toString)
        .set("rqUID", LocalDateTime.now(ZoneId.of("GMT")).toString)
    }
    .exec(samplePayment)
    .pause(50 milliseconds, 100 milliseconds)

  /* Stop the test when response time is greather than 500 ms or any error occured */
  setUp(
    scn.inject(
      incrementUsersPerSec(1)
        .times(10)
        .eachLevelLasting(2 minutes)
    )
  ).protocols(httpProtocol)
}
