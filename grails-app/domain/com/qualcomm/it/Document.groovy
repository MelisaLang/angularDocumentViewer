package com.qualcomm.it
class Document {

	String documentId
	String author
	Date pubDate
	String docDescription
	
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		documentId size: 3..64, unique: true, blank: false
		author size:3..64, blank:false
		docDescription size:3..256, blank:false
		pubDate nullable:true
    }
	
	static mapping = {
		autoTimestamp true		// true by default
	}
	
	String toString() { return "Document $documentId (id: $id)" }  // returns a diagnostic string for log messages and debugging
	String getDisplayString() { return documentId }	// creates a read-only displayString property for the scaffolding
}
