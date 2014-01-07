html-validator
==============

Maven project to check the validaty of HTML files (based on the W3C API)

You have to use the com.jroche.HtmlValidatorRunner with the following arguments:  

---

__--verbose__ (display all informations of the runner. Default, no verbose will be done)  
*__--extensions html tpl tmpl__* (optional, default: htm html tmpl tpl)  
__--directories . ../templates__ C:/Dev/templates  
*__--subdirectories__* (optional, default: false)  
*__--output result.xml__* (optional, default: ./report-html-checkstyle.xml)  
*__--encoding__* (optional, default: UTF-8)  

---
  
  
Example:  
```cmd
java com.jroche.HtmlValidatorRunner --verbose --encoding UTF-16  
```
