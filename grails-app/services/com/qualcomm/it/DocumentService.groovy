package com.qualcomm.it
import grails.transaction.Transactional

class DocumentException extends RuntimeException {
	String message
	Document document
}

@Transactional
class DocumentService {

    def createDocument(String _documentId, String _author, String _docDescription, Date _pubDate) {
		def document = new Document(documentId: _documentId, author: _author, docDescription: _docDescription, pubDate: _pubDate)
		if (document.save()) {
			return document
		} else {
			throw new DocumentException(
				message: "Invalid or empty document", document: document
				)
		}
    }
}
