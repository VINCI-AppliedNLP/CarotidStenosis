package listeners

import gov.va.vinci.leo.listener.SimpleXmiListener
import gov.va.vinci.leo.tools.LeoUtils

String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")
String xmiDir =  "data/output"



if(!(new File(xmiDir)).exists()) (new File(xmiDir)).mkdirs()

listener = new SimpleXmiListener(new File(xmiDir))
listener.setTypeSystemDescriptor(new File("generated-types/resources/types/TypeSystem.xml"))
listener.setLaunchAnnotationViewer(true)

