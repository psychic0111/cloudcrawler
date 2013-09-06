
package com.xdtech.platform.webcrawler.ws.webdb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xdtech.platform.webcrawler.ws.webdb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UpdateResponse_QNAME = new QName("http://www.xd-tech.com", "updateResponse");
    private final static QName _AddOrUpdateURLsResponse_QNAME = new QName("http://www.xd-tech.com", "addOrUpdateURLsResponse");
    private final static QName _DeleteResponse_QNAME = new QName("http://www.xd-tech.com", "deleteResponse");
    private final static QName _FindWebDbByQueryResponse_QNAME = new QName("http://www.xd-tech.com", "findWebDbByQueryResponse");
    private final static QName _UpdateByMD5_QNAME = new QName("http://www.xd-tech.com", "updateByMD5");
    private final static QName _UpdateByMD5Response_QNAME = new QName("http://www.xd-tech.com", "updateByMD5Response");
    private final static QName _FindWebDbByQueryFromTable_QNAME = new QName("http://www.xd-tech.com", "findWebDbByQueryFromTable");
    private final static QName _Delete_QNAME = new QName("http://www.xd-tech.com", "delete");
    private final static QName _AddUrlResponse_QNAME = new QName("http://www.xd-tech.com", "addUrlResponse");
    private final static QName _FindWebDbByQuery_QNAME = new QName("http://www.xd-tech.com", "findWebDbByQuery");
    private final static QName _Exception_QNAME = new QName("http://www.xd-tech.com", "Exception");
    private final static QName _Update_QNAME = new QName("http://www.xd-tech.com", "update");
    private final static QName _AddOrUpdateURLs_QNAME = new QName("http://www.xd-tech.com", "addOrUpdateURLs");
    private final static QName _ClearDBResponse_QNAME = new QName("http://www.xd-tech.com", "clearDBResponse");
    private final static QName _ClearDB_QNAME = new QName("http://www.xd-tech.com", "clearDB");
    private final static QName _AddUrl_QNAME = new QName("http://www.xd-tech.com", "addUrl");
    private final static QName _FindWebDbByQueryFromTableResponse_QNAME = new QName("http://www.xd-tech.com", "findWebDbByQueryFromTableResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xdtech.platform.webcrawler.ws.webdb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddUrlResponse }
     * 
     */
    public AddUrlResponse createAddUrlResponse() {
        return new AddUrlResponse();
    }

    /**
     * Create an instance of {@link WebDBCrawlerResult }
     * 
     */
    public WebDBCrawlerResult createWebDBCrawlerResult() {
        return new WebDBCrawlerResult();
    }

    /**
     * Create an instance of {@link ClearDB }
     * 
     */
    public ClearDB createClearDB() {
        return new ClearDB();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link WebDbCrawler }
     * 
     */
    public WebDbCrawler createWebDbCrawler() {
        return new WebDbCrawler();
    }

    /**
     * Create an instance of {@link UpdateByMD5Response }
     * 
     */
    public UpdateByMD5Response createUpdateByMD5Response() {
        return new UpdateByMD5Response();
    }

    /**
     * Create an instance of {@link FindWebDbByQueryFromTableResponse }
     * 
     */
    public FindWebDbByQueryFromTableResponse createFindWebDbByQueryFromTableResponse() {
        return new FindWebDbByQueryFromTableResponse();
    }

    /**
     * Create an instance of {@link FindWebDbByQueryFromTable }
     * 
     */
    public FindWebDbByQueryFromTable createFindWebDbByQueryFromTable() {
        return new FindWebDbByQueryFromTable();
    }

    /**
     * Create an instance of {@link AddOrUpdateURLsResponse }
     * 
     */
    public AddOrUpdateURLsResponse createAddOrUpdateURLsResponse() {
        return new AddOrUpdateURLsResponse();
    }

    /**
     * Create an instance of {@link UrlInfoCrawler }
     * 
     */
    public UrlInfoCrawler createUrlInfoCrawler() {
        return new UrlInfoCrawler();
    }

    /**
     * Create an instance of {@link AddUrl }
     * 
     */
    public AddUrl createAddUrl() {
        return new AddUrl();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link ClearDBResponse }
     * 
     */
    public ClearDBResponse createClearDBResponse() {
        return new ClearDBResponse();
    }

    /**
     * Create an instance of {@link AddOrUpdateURLs }
     * 
     */
    public AddOrUpdateURLs createAddOrUpdateURLs() {
        return new AddOrUpdateURLs();
    }

    /**
     * Create an instance of {@link FindWebDbByQueryResponse }
     * 
     */
    public FindWebDbByQueryResponse createFindWebDbByQueryResponse() {
        return new FindWebDbByQueryResponse();
    }

    /**
     * Create an instance of {@link UpdateByMD5 }
     * 
     */
    public UpdateByMD5 createUpdateByMD5() {
        return new UpdateByMD5();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link FindWebDbByQuery }
     * 
     */
    public FindWebDbByQuery createFindWebDbByQuery() {
        return new FindWebDbByQuery();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOrUpdateURLsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "addOrUpdateURLsResponse")
    public JAXBElement<AddOrUpdateURLsResponse> createAddOrUpdateURLsResponse(AddOrUpdateURLsResponse value) {
        return new JAXBElement<AddOrUpdateURLsResponse>(_AddOrUpdateURLsResponse_QNAME, AddOrUpdateURLsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindWebDbByQueryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "findWebDbByQueryResponse")
    public JAXBElement<FindWebDbByQueryResponse> createFindWebDbByQueryResponse(FindWebDbByQueryResponse value) {
        return new JAXBElement<FindWebDbByQueryResponse>(_FindWebDbByQueryResponse_QNAME, FindWebDbByQueryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateByMD5 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "updateByMD5")
    public JAXBElement<UpdateByMD5> createUpdateByMD5(UpdateByMD5 value) {
        return new JAXBElement<UpdateByMD5>(_UpdateByMD5_QNAME, UpdateByMD5 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateByMD5Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "updateByMD5Response")
    public JAXBElement<UpdateByMD5Response> createUpdateByMD5Response(UpdateByMD5Response value) {
        return new JAXBElement<UpdateByMD5Response>(_UpdateByMD5Response_QNAME, UpdateByMD5Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindWebDbByQueryFromTable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "findWebDbByQueryFromTable")
    public JAXBElement<FindWebDbByQueryFromTable> createFindWebDbByQueryFromTable(FindWebDbByQueryFromTable value) {
        return new JAXBElement<FindWebDbByQueryFromTable>(_FindWebDbByQueryFromTable_QNAME, FindWebDbByQueryFromTable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUrlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "addUrlResponse")
    public JAXBElement<AddUrlResponse> createAddUrlResponse(AddUrlResponse value) {
        return new JAXBElement<AddUrlResponse>(_AddUrlResponse_QNAME, AddUrlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindWebDbByQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "findWebDbByQuery")
    public JAXBElement<FindWebDbByQuery> createFindWebDbByQuery(FindWebDbByQuery value) {
        return new JAXBElement<FindWebDbByQuery>(_FindWebDbByQuery_QNAME, FindWebDbByQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOrUpdateURLs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "addOrUpdateURLs")
    public JAXBElement<AddOrUpdateURLs> createAddOrUpdateURLs(AddOrUpdateURLs value) {
        return new JAXBElement<AddOrUpdateURLs>(_AddOrUpdateURLs_QNAME, AddOrUpdateURLs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearDBResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "clearDBResponse")
    public JAXBElement<ClearDBResponse> createClearDBResponse(ClearDBResponse value) {
        return new JAXBElement<ClearDBResponse>(_ClearDBResponse_QNAME, ClearDBResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearDB }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "clearDB")
    public JAXBElement<ClearDB> createClearDB(ClearDB value) {
        return new JAXBElement<ClearDB>(_ClearDB_QNAME, ClearDB.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "addUrl")
    public JAXBElement<AddUrl> createAddUrl(AddUrl value) {
        return new JAXBElement<AddUrl>(_AddUrl_QNAME, AddUrl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindWebDbByQueryFromTableResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "findWebDbByQueryFromTableResponse")
    public JAXBElement<FindWebDbByQueryFromTableResponse> createFindWebDbByQueryFromTableResponse(FindWebDbByQueryFromTableResponse value) {
        return new JAXBElement<FindWebDbByQueryFromTableResponse>(_FindWebDbByQueryFromTableResponse_QNAME, FindWebDbByQueryFromTableResponse.class, null, value);
    }

}
