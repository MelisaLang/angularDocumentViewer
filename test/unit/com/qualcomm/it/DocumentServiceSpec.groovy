package com.qualcomm.it

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DocumentService)
class DocumentServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
	
	def "Invalid documents generate exceptions"() {
		given: "A new document in the db"
		new Document(documentId: "testDoc", author:"Lisa Lang", docDescription: "Test document")
		
		when: "an invalid document creation is attempted"
		def newDocument = service.createDocument(null, null, null, null)
		
		then: "an exception is thrown and no document is saved"
		thrown(DocumentException)
	}
}
