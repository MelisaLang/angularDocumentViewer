import com.qualcomm.it.Document
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:
				createSampleData()
				break;
			case Environment.TEST:
				createTestData()
				break;
			case Environment.PRODUCTION:
				break;
		}
    }
    def destroy = {
    }
	
	def createSampleData = {
		new Document(documentId: "doc1", author:"Lisa Lang", docDescription:"Guide to Grails", pubDate:new Date().parse("dd/MMM/yyyy", "05/Mar/2015")).save(failOnError:true)
		new Document(documentId: "doc2", author:"Sheryl Sandberg", docDescription:"Lean In").save(failOnError:true)
		new Document(documentId: "doc3", author:"Richard O'Connor", docDescription:"Rewire Your Brain", pubDate:new Date().parse("dd/MMM/yyyy", "21/Mar/2014")).save(failOnError:true)
	}
}
