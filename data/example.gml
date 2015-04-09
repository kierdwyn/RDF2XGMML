graph [
node [
 id 1
 label "1"
 displayName  "John@somewhere.com  "
 width  "18  "
]
node [
 id 4
 label "4"
 displayName  "John Smith  "
 width  "10  "
]
node [
 id 6
 label "6"
 displayName  "Smith  "
 width  "5  "
]
node [
 id 0
 label "0"
 displayName  "[]  "
 width  "2  "
]
node [
 id 7
 label "7"
 displayName  "John  "
 width  "4  "
]
node [
 id 2
 label "2"
 displayName  "http://www.w3.org/2001/vcard-rdf/3.0#internet  "
 width  "45  "
]
node [
 id 5
 label "5"
 displayName  "[]  "
 width  "2  "
]
node [
 id 3
 label "3"
 displayName  "http://somewhere/JohnSmith/  "
 width  "27  "
]
 edge [
  source 0
  target 1
  label "0-&gt;1"
 displayName "rdf:value"
]
 edge [
  source 0
  target 2
  label "0-&gt;2"
 displayName "rdf:type"
]
 edge [
  source 3
  target 4
  label "3-&gt;4"
 displayName "vcard:FN"
]
 edge [
  source 3
  target 5
  label "3-&gt;5"
 displayName "vcard:N"
]
 edge [
  source 3
  target 0
  label "3-&gt;0"
 displayName "vcard:EMAIL"
]
 edge [
  source 5
  target 6
  label "5-&gt;6"
 displayName "vcard:Family"
]
 edge [
  source 5
  target 7
  label "5-&gt;7"
 displayName "vcard:Given"
]
]
