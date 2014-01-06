html-validator
==============

Maven project to check the validaty of HTML files (based on the W3C API)

You have to use the com.jroche.HtmlValidatorRunner with the following arguments:
--verbose (display all informations of the runner. Default, no verbose will be done)
--extensions html tpl tmpl (optional, default: htm html tmpl tpl)
--directories . ../templates C:/Dev/templates
--subdirectories (optional, default: false)
--output result.xml (optional, default: ./report-html-checkstyle.xml)
--encoding (optional, default: UTF-8)

Example:
java com.jroche.HtmlValidatorRunner --verbose --encoding UTF-16