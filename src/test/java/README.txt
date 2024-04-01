Keiji test CreateDDLMySQL
Zachary test EdgeConvertCreateDDL
Trydr test EdgeConvertFileParser
Kyle test EdgeField
Brandon test EdgeTable

Ensure that a JDK >= 16 is installed
cd to the directory
Run the tests: build.gradle test

Whats wrong with any of the files:

EdgeFieldTest: One method was written to fail.
EdgeConvertFileParserTest: We couldn't get the Fields to save correctly so we just manually set them. It's a hack fix, but that's the only issue we had.

CreateDDLMySQLTest: The problem is the related tables test. We were trying to get the generated sql to have the relationships, but its not generating at all for the foreign keys.