package com.qualcomm.it

import static org.springframework.http.HttpStatus.*

import java.util.Date;
import grails.transaction.Transactional
import grails.validation.Validateable

@Transactional(readOnly = true)
class DocumentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	static scaffold = true
	static defaultAction = "index"

	def documentService
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Document.list(params), model:[documentInstanceCount: Document.count()]
    }

	def search() {}
	
	def results(String documentId) {
		def documents = Document.where { documentId =~ documentId
		}.list()
		return [ documents: documents,
			     term: params.documentId,
				 totalDocuments: Document.count() ]
	}
	
//	def addDocument(String _documentId, String _author, String _docDescription, Date _pubDate) {
//		try {
//			def newDocument = documentService.createDocument(_documentId, _author, _docDescription, _pubDate)
//			flash.message = "Added new document: ${newDocument.docDescription}"
//		} catch (DocumentException de) {
//			flash.messge = de.message
//		}
//		redirect(action: 'create', documentId: _documentId)
//	}
	def addDocument(DocumentAddCommand dac) {
		if (dac.hasErrors()) {
			render view: "create", model: [ document: dac]
		} else {
			def document = new Document( dac.properties )
			if (document.validate() && document.save()) {
				flash.message = "Congratulations!  Added new Document ${documentId}"
				redirect(uri: '/')
			} else {
				return [ document : dac]
			}
		}
	}


	    def show(Document documentInstance) {
        respond documentInstance
    }

    def create() {
        respond new Document(params)
    }

    @Transactional
    def save(Document documentInstance) {
        if (documentInstance == null) {
            notFound()
            return
        }

        if (documentInstance.hasErrors()) {
            respond documentInstance.errors, view:'create'
            return
        }

        documentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'document.label', default: 'Document'), documentInstance.id])
                redirect documentInstance
            }
            '*' { respond documentInstance, [status: CREATED] }
        }
    }

    def edit(Document documentInstance) {
        respond documentInstance
    }

    @Transactional
    def update(Document documentInstance) {
        if (documentInstance == null) {
            notFound()
            return
        }

        if (documentInstance.hasErrors()) {
            respond documentInstance.errors, view:'edit'
            return
        }

        documentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Document.label', default: 'Document'), documentInstance.id])
                redirect documentInstance
            }
            '*'{ respond documentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Document documentInstance) {

        if (documentInstance == null) {
            notFound()
            return
        }

        documentInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Document.label', default: 'Document'), documentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	@Validateable
	class DocumentAddCommand {
		String documentId
		String author
		String docDescription
		Date pubDate
		
		static constraints = {
			importFrom Document
		}
	}
	
}
