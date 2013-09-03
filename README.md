VLiceneHeaderUtil
=================


Allows to automatically change license headers of `.java` source files based on a template.

## Requirements:

Only `.java` files can be processed and only if they contain a correct package declaration such as `package my.package;`.
Everything before the package declaration will be replaced by the header.

## How To Use It:

    LicenseHeaderUtil.changeLicenseHeaderInDir(
        Paths.get("sample-project/src"),
        Paths.get("sample-project/src-out"), // could also be the same as above (overwrites src files)
        Paths.get("sample-project/license-template.txt")
    );
    
## Template File:

The template contains the license header. VLiceneHeaderUtil provides the actual file name via a variable `${VRL-LICENSE-HEADER-FILE-NAME}`


Example: 

    /* ${VRL-LICENSE-HEADER-FILE-NAME}
     *
     * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
     * 
     * THE LICENSE...
     */
    
## How To Use The Gradle Plugin

To use the plugin add the following line to the `build.gradle` file:

    apply from: 'http://gradle-plugins.mihosoft.eu/latest/vlicenseheader.txt'
    
Now specify the license header template.

Example:

Specify the header as string:

    repairHeaders.licenseHeaderText = 'license text'
    
or specify a file taht contains the template:

    repairHeaders.licenseHeaderText = new File(projectDir,'license-template.txt')


    
    
