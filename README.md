# JsonSerializer
Desktop application. Serializer in JSON format.

 Can write objects (Maps, Collections, Arrays, SimpleValue) to lines and Streams.
 Annotation support for:
O Changing the name of the property
O Ignoring the property during serialization
 Support for transient fields (transient int x;):
O By default, ignore them
O If JsonProperty is annotated on it - serialize.
 Support as fields
O By default, all public fields
O Private fields marked with a JsonProperty annotation
In the case of a name conflict, priority is given to the entity on which there is an annotation
 Possibility of JSON output in a compact form (without extra spaces, without enters), and in readable form (with indents, line breaks, spaces around ":")
 Write null values.
