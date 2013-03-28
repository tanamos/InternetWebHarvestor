package Beans;

/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Ordering;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
		import org.carrot2.core.LanguageCode;
/*     */ import org.carrot2.util.MapUtils;
/*     */ import org.carrot2.util.simplexml.SimpleXmlWrapperValue;
/*     */ import org.carrot2.util.simplexml.SimpleXmlWrappers;
/*     */ import org.codehaus.jackson.annotate.JsonAutoDetect;
/*     */ import org.codehaus.jackson.annotate.JsonProperty;
/*     */ import org.codehaus.jackson.map.annotate.JsonSerialize;
/*     */ import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/*     */ import org.simpleframework.xml.Attribute;
/*     */ import org.simpleframework.xml.Element;
/*     */ import org.simpleframework.xml.ElementList;
/*     */ import org.simpleframework.xml.ElementMap;
/*     */ import org.simpleframework.xml.Root;
/*     */ 
/*     */ @Root(name="document")
/*     */ @JsonAutoDetect({org.codehaus.jackson.annotate.JsonMethod.NONE})
/*     */ @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
/*     */ public final class Document
/*     */ {
/*     */   public static final String TITLE = "title";
/*     */   public static final String SUMMARY = "snippet";
/*     */   public static final String CONTENT_URL = "url";
/*     */   public static final String CLICK_URL = "click-url";
/*     */   public static final String THUMBNAIL_URL = "thumbnail-url";
/*     */   public static final String SIZE = "size";
/*     */   public static final String SCORE = "score";
/*     */   public static final String SOURCES = "sources";
/*     */   public static final String LANGUAGE = "language";
/*     */   public static final String PARTITIONS = "partitions";
/* 114 */   private final Map<String, Object> fields = Maps.newHashMap();
/*     */ 
/* 117 */   private final Map<String, Object> fieldsView = Collections.unmodifiableMap(this.fields);
/*     */ 
/*     */   @Attribute(required=false)
/*     */   Integer id;
/*     */   private ArrayList<IDocumentSerializationListener> serializationListeners;
/* 556 */   public static final Comparator<Document> BY_ID_COMPARATOR = Ordering.natural().nullsFirst().onResultOf(DocumentToId.INSTANCE);
/*     */ 
/*     */   public Document()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Document(String title)
/*     */   {
/* 145 */     this(title, null);
/*     */   }
/*     */ 
/*     */   public Document(String title, String summary)
/*     */   {
/* 153 */     this(title, summary, (String)null);
/*     */   }
/*     */ 
/*     */   public Document(String title, String summary, LanguageCode language)
/*     */   {
/* 162 */     this(title, summary, null, language);
/*     */   }
/*     */ 
/*     */   public Document(String title, String summary, String contentUrl)
/*     */   {
/* 171 */     this(title, summary, contentUrl, null);
/*     */   }
/*     */ 
/*     */   public Document(String title, String summary, String contentUrl, LanguageCode language)
/*     */   {
/* 180 */     setField("title", title);
/* 181 */     setField("snippet", summary);
/*     */ 
/* 183 */     if (StringUtils.isNotBlank(contentUrl))
/*     */     {
/* 185 */       setField("url", contentUrl);
/*     */     }
/*     */ 
/* 188 */     if (language != null)
/*     */     {
/* 190 */       setField("language", language);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document(String title, String summary, String contentUrl, LanguageCode language, int id)
/*     */   {
/* 200 */     this(title, summary, contentUrl, language);
/* 201 */     this.id = Integer.valueOf(id);
/*     */   }
/*     */ 
/*     */   @JsonProperty
/*     */   public Integer getId()
/*     */   {
/* 214 */     return this.id;
/*     */   }
/*     */ 
/*     */   @JsonProperty
/*     */   @Element(required=false)
/*     */   public String getTitle()
/*     */   {
/* 224 */     return (String)getField("title");
/*     */   }
/*     */ 
/*     */   @Element(required=false)
/*     */   public Document setTitle(String title)
/*     */   {
/* 236 */     return setField("title", title);
/*     */   }
/*     */ 
/*     */   @JsonProperty("snippet")
/*     */   @Element(name="snippet", required=false)
/*     */   public String getSummary()
/*     */   {
/* 246 */     return (String)getField("snippet");
/*     */   }
/*     */ 
/*     */   @Element(name="snippet", required=false)
/*     */   public Document setSummary(String summary)
/*     */   {
/* 258 */     return setField("snippet", summary);
/*     */   }
/*     */ 
/*     */   @JsonProperty("url")
/*     */   @Element(name="url", required=false)
/*     */   public String getContentUrl()
/*     */   {
/* 268 */     return (String)getField("url");
/*     */   }
/*     */ 
/*     */   @Element(name="url", required=false)
/*     */   public Document setContentUrl(String contentUrl)
/*     */   {
/* 280 */     return setField("url", contentUrl);
/*     */   }
/*     */ 
/*     */   @JsonProperty
/*     */   @ElementList(entry="source", required=false)
/*     */   public List<String> getSources()
/*     */   {
/* 290 */     return (List)getField("sources");
/*     */   }
/*     */ 
/*     */   @ElementList(entry="source", required=false)
/*     */   public Document setSources(List<String> sources)
/*     */   {
/* 302 */     return setField("sources", sources);
/*     */   }
/*     */ 
/*     */   public LanguageCode getLanguage()
/*     */   {
/* 310 */     return (LanguageCode)getField("language");
/*     */   }
/*     */ 
/*     */   public Document setLanguage(LanguageCode language)
/*     */   {
/* 321 */     return setField("language", language);
/*     */   }
/*     */ 
/*     */   @Attribute(name="score", required=false)
/*     */   public Double getScore()
/*     */   {
/* 332 */     return (Double)getField("score");
/*     */   }
/*     */ 
/*     */   @Attribute(name="score", required=false)
/*     */   public Document setScore(Double score)
/*     */   {
/* 344 */     return setField("score", score);
/*     */   }
/*     */ 
/*     */   @JsonProperty("language")
/*     */   @Attribute(required=false, name="language")
/*     */   private String getLanguageIsoCode()
/*     */   {
/* 352 */     LanguageCode language = getLanguage();
/* 353 */     return language != null ? language.getIsoCode() : null;
/*     */   }
/*     */ 
/*     */   @Attribute(required=false, name="language")
/*     */   private void setLanguageIsoCode(String languageIsoCode)
/*     */   {
/* 360 */     if (languageIsoCode != null)
/*     */     {
/* 362 */       LanguageCode language = LanguageCode.forISOCode(languageIsoCode);
/* 363 */       if (language != null)
/*     */       {
/* 365 */         setLanguage(language);
/*     */       }
/*     */       else
/*     */       {
/* 370 */         setLanguage(LanguageCode.valueOf(languageIsoCode));
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 375 */       setLanguage(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   @JsonProperty("fields")
/*     */   private Map<String, Object> getOtherFields()
/*     */   {
/*     */     Map otherFields;
/* 391 */     synchronized (this)
/*     */     {
/* 393 */       otherFields = Maps.newHashMap(this.fields);
/*     */     }
/* 395 */     otherFields.remove("title");
/* 396 */     otherFields.remove("snippet");
/* 397 */     otherFields.remove("url");
/* 398 */     otherFields.remove("sources");
/* 399 */     otherFields.remove("language");
/* 400 */     otherFields.remove("score");
/* 401 */     fireSerializationListeners(otherFields);
/* 402 */     return otherFields.isEmpty() ? null : otherFields;
/*     */   }
/*     */ 
/*     */   @ElementMap(entry="field", key="key", attribute=true, inline=true, required=false)
/*     */   private HashMap<String, SimpleXmlWrapperValue> getOtherFieldsXml()
/*     */   {
/*     */     HashMap otherFieldsForSerialization;
/* 413 */     synchronized (this)
/*     */     {
/* 415 */       otherFieldsForSerialization = MapUtils.asHashMap(SimpleXmlWrappers.wrap(this.fields));
/*     */     }
/*     */ 
/* 418 */     otherFieldsForSerialization.remove("title");
/* 419 */     otherFieldsForSerialization.remove("snippet");
/* 420 */     otherFieldsForSerialization.remove("url");
/* 421 */     otherFieldsForSerialization.remove("sources");
/* 422 */     otherFieldsForSerialization.remove("language");
/* 423 */     otherFieldsForSerialization.remove("score");
/* 424 */     fireSerializationListeners(otherFieldsForSerialization);
/* 425 */     return otherFieldsForSerialization.isEmpty() ? null : otherFieldsForSerialization;
/*     */   }
/*     */ 
/*     */   @ElementMap(entry="field", key="key", attribute=true, inline=true, required=false)
/*     */   private void setOtherFieldsXml(HashMap<String, SimpleXmlWrapperValue> otherFieldsForSerialization)
/*     */   {
/* 436 */     if (otherFieldsForSerialization != null)
/*     */     {
/* 440 */       this.fields.putAll(SimpleXmlWrappers.unwrap(otherFieldsForSerialization));
/*     */     }
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getFields()
/*     */   {
/* 451 */     return this.fieldsView;
/*     */   }
/*     */ 
/*     */   public <T> T getField(String name)
/*     */   {
/* 464 */     synchronized (this)
/*     */     {
/* 466 */       return (T) this.fields.get(name);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document setField(String name, Object value)
/*     */   {
/* 479 */     synchronized (this)
/*     */     {
/* 481 */       this.fields.put(name, value);
/*     */     }
/* 483 */     return this;
/*     */   }
/*     */ 
/*     */   public static void assignDocumentIds(Collection<Document> documents)
/*     */   {
/*     */     int maxId;
/* 498 */     synchronized (documents)
/*     */     {
/* 500 */       HashSet ids = Sets.newHashSet();
/*     */ 
/* 504 */       maxId = -2147483648;
/* 505 */       for (Document document : documents)
/*     */       {
/* 507 */         if (document.id != null)
/*     */         {
/* 509 */           if (ids.add(document.id))
/*     */           {
/* 511 */             maxId = Math.max(maxId, document.id.intValue());
/*     */           }
/*     */           else
/*     */           {
/* 515 */             document.id = null;
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 521 */       maxId = Math.max(maxId, -1);
/*     */ 
/* 524 */       for (Document document : documents)
/*     */       {
/* 526 */         if (document.id == null)
/*     */         {
/* 528 */           document.id = Integer.valueOf(++maxId);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addSerializationListener(IDocumentSerializationListener listener)
/*     */   {
/* 566 */     synchronized (this)
/*     */     {
/* 568 */       if (this.serializationListeners == null)
/*     */       {
/* 570 */         this.serializationListeners = Lists.newArrayList();
/*     */       }
/* 572 */       this.serializationListeners.add(listener);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void fireSerializationListeners(Map<String, ?> otherFieldsForSerialization)
/*     */   {
/* 598 */     synchronized (this)
/*     */     {
/* 600 */       if (this.serializationListeners != null)
/*     */       {
/* 602 */         for (IDocumentSerializationListener listener : this.serializationListeners)
/*     */         {
/* 604 */           listener.beforeSerialization(this, otherFieldsForSerialization);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract interface IDocumentSerializationListener
/*     */   {
/*     */     public abstract void beforeSerialization(Document paramDocument, Map<String, ?> paramMap);
/*     */   }
/*     */ 
/*     */   public static final class DocumentToId
/*     */     implements Function<Document, Integer>
/*     */   {
/* 540 */     public static final DocumentToId INSTANCE = new DocumentToId();
/*     */ 
/*     */     public Integer apply(Document document)
/*     */     {
/* 548 */       return document.id;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\L33905\Documents\carrot2-java-api-3.6.2\carrot2-java-api-3.6.2\lib\required\carrot2-core-3.6.2.jar
 * Qualified Name:     org.carrot2.core.Document
 * JD-Core Version:    0.6.2
 */