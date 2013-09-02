VLiceneHeaderUtil
=================


Allows to automatically change license headers of a file based on a template.

## Requirements:

Only `.java` files can be processed and only if they contain a correct package declaration such as `package my.package;`.

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
    
