# JsonSerializer
Desktop application. Serializer in JSON format.

1. Can write objects (Maps, Collections, Arrays, SimpleValue) to lines and Streams.
2. Annotation support for:
- Changing the name of the property
- Ignoring the property during serialization
3. Support for transient fields (transient int x;):
- By default, ignore them
- If JsonProperty is annotated on it - serialize.
4. Support as fields
- By default, all public fields
- Private fields marked with a JsonProperty annotation
- In the case of a name conflict, priority is given to the entity on which there is an annotation
5. Possibility of JSON output in a compact form (without extra spaces, without enters), and in readable form (with indents, line breaks, spaces around ":")
6. Write null values.
