## RDF

[RDF教程](http://jena.apache.org/tutorials/rdf_api.html#ch-Writing\_RDF)

RDF是一个三元组模型，即每一份知识都可以被分解为如下形式(subject(主)，predicate(谓)，object(宾))

## vCard
W3为了满足一些新需要，定义了另一套规范(http://www.w3.org/TR/vcard-rdf)，
它用RDF(Resource Description Framework)来描述vCard，表示的内容一样，只是用XML来的描述的。如：

    <vCard:Family> Crystal </vCard:Family>
    <vCard:Given>  Corky </vCard:Given>
     <vCard:Other>  Jacky </vCard:Other>
     <vCard:Prefix> Dr </vCard:Prefix>

### Jena中的vcard
uri: [http://www.w3.org/2001/vcard-rdf/3.0](http://www.w3.org/2001/vcard-rdf/3.0)

    ORGPROPERTIES： orgproperties: 组织
    ADRTYPES:       adrtypes: 
    NPROPERTIES     nproperties:
    EMAILTYPES      emailtypes
    TELTYPES        teltypes
    ADRPROPERTIES   adrpoperties
    TZTYPES         tzitypes
    Street          street
    AGENT           agent
    Source          source
    LOGO            logo
    BDAY            bady
    REV             rev
    SORT_STRING     sort_string
    Orgname         orgname
    CATEGORIES      categories
    N               n
    Pcode           pcode
    Prefix          prefix
    PHOTO           photo
    FN              fn
    ORG             org
    Suffix          suffix
    CLASS           class
    ADR             adr
    Region          region
    GEO             geo
    Extadd          extadd
    EMAIL           email
    UID             uid
    Family          family
    TZ              tz
    Name            name
    Orgunit         orgunit
    Country         country
    SOUND           sound
    Title           title
    NOTE            note
    MAILER          maller
    Other           other
    Locality        locality
    Pobox           pobox
    KEY             key
    PRODID          prodid
    Given           given
    LABEL           label
    TEL             tel
    NICKNAME        nickname
    ROLE            role

## Statements
Each arc in an RDF Model is called a statement. Each statement asserts a fact about a resource. A statement has three parts:

* the subject is the resource from which the arc leaves
* the predicate is the property that labels the arc
* the object is the resource or literal pointed to by the arc

A statement is sometimes called a triple, because of its three parts.

    http://somewhere/JohnSmith http://www.w3.org/2001/vcard-rdf/3.0#N 21fa9cc9-7980-4aa1-a985-59b57307dbbf .
    三列数据，分别代表 subject predicate object 
    
### Writing RDF

    model.write(System.out);  OutputStream
    <rdf:RDF
        xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
        xmlns:vcard="http://www.w3.org/2001/vcard-rdf/3.0#">
      <rdf:Description rdf:about="http://somewhere/JohnSmith">
        <vcard:N rdf:parseType="Resource">
          <vcard:Family>Smith</vcard:Family>
          <vcard:Given>John</vcard:Given>
        </vcard:N>
        <vcard:FN>John Smith</vcard:FN>
      </rdf:Description>
    </rdf:RDF>

    如果能通过图片展示 就更好了。
    
    model.write(System.out): 
    model.write(System.out,"RDF/XML-ABBREV")
    xml格式的数据
    
    
    
    model.write(System.out,""N-TRIPLES"") 适合大型的model
    <http://somewhere/JohnSmith> <http://www.w3.org/2001/vcard-rdf/3.0#N> _:Bc68ccff7X2D3cd7X2D4974X2Db8f2X2D007e9296196a .
    <http://somewhere/JohnSmith> <http://www.w3.org/2001/vcard-rdf/3.0#FN> "John Smith" .
    _:Bc68ccff7X2D3cd7X2D4974X2Db8f2X2D007e9296196a <http://www.w3.org/2001/vcard-rdf/3.0#Family> "Smith" .
    _:Bc68ccff7X2D3cd7X2D4974X2Db8f2X2D007e9296196a <http://www.w3.org/2001/vcard-rdf/3.0#Given> "John" .
    

## Reading RDF
    
    InputStream in = FileManager.get().open( inputFileName );
    model.read(in, "");
    model.write(System.out,"N-TRIPLES");
    

## Controlling Prefix


##Jena RDF Packages
Jena is a Java API for semantic web applications. The key RDF package for the application developer
is org.apache.jena.rdf.model. The api has been defined in terms of interfaces so that application code 
can work with different implements without change. This package contains interfaces for representing models,
resource,properties,literals,statements and all the other key concepts of RDF, and a ModelFactory for creating models.

## Navigating a Model
    
    Resource 就是主语 subject 主语
    predicate 就是谓语
    RDFNode 就是宾语 object
    
    String fullName = vcard.getProperty(VCARD.FN)   // 因为一般意义上的object 都是 一个 字符串。
                    .getString();
            System.out.println(fullName);
    
            vcard.addProperty(VCARD.NICKNAME,"Smithy")
                    .addProperty(VCARD.NICKNAME,"Adman");
    
             StmtIterator iter =vcard.listProperties(VCARD.NICKNAME);
            while(iter.hasNext())
            {
                System.out.println("   "+ iter.nextStatement().getObject().toString());
            }

## Querying a model
Searching a model. The core Jena API supports only a limited query primitive. The more powerful query
facilities of SPARQL are described elsewhere
    
    getProperty： 返回的是RDF
    getProperty.getString() 类似于 getSubject 就是返回宾语

使用Selector来完成选择

    StmtIterator iter = model.listStatements(
                new SimpleSelector(null,VCARD.FN,(RDFNode) null){
                    @Override
                    public boolean selects(Statement s) {
                        return s.getString().endsWith("Smith");
                    }
                });


## Operations on Models

Jena provides three operations for manipulating Models as a 
whole. These are the common set operations of union, 
intersection and difference.

    union: // merge the graphs
                   Model model = model1.union(model2);
                   

Containers

    

## More about Literals  and 