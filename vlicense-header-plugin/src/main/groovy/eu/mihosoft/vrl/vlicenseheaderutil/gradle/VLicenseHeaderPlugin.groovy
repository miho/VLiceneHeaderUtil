package eu.mihosoft.vrl.vlicenseheaderutil.gradle;
 
import org.gradle.api.*;
import java.nio.file.Paths;
import java.nio.file.Path;
 
class VLicenseHeaderPlugin implements Plugin {
    def void apply(Object project) {
        
        project.extensions.create("repairHeaders", VLicenseHeaderPluginExtension)
        
        project.task('repairHeaders') << {
            println ">> VLicenseHeaderPlugin: processing headers"
            project.sourceSets.all {
                allJava.getSrcDirs().each() {
                    dir->
                    if (dir.exists()) {
                        if (project.repairHeaders.licenseHeaderText instanceof String) {
                            eu.mihosoft.vrl.licenseheaderutil.LicenseHeaderUtil.
                                changeLicenseHeaderInDir(
                                Paths.get(dir.getAbsolutePath()),
                                Paths.get(dir.getAbsolutePath()),
                                project.repairHeaders.licenseHeaderText);
                        } else if (project.repairHeaders.licenseHeaderText instanceof File) {
                            eu.mihosoft.vrl.licenseheaderutil.LicenseHeaderUtil.
                                changeLicenseHeaderInDir(
                                Paths.get(dir.getAbsolutePath()),
                                Paths.get(dir.getAbsolutePath()),
                                Paths.get(project.repairHeaders.licenseHeaderText.getAbsolutePath()));
                        } else if (project.repairHeaders.licenseHeaderText instanceof Path) {
                            eu.mihosoft.vrl.licenseheaderutil.LicenseHeaderUtil.
                                changeLicenseHeaderInDir(
                                Paths.get(dir.getAbsolutePath()),
                                Paths.get(dir.getAbsolutePath()),
                                project.repairHeaders.licenseHeaderText);
                        } else {
                            System.err.println("type of project.licenseHeaderText not supported."+
                                "supported types: java.lang.String, java.io.File, java.nio.file.Path")
                        }
                        
                    }
                }
            }
        }
    }
}

class VLicenseHeaderPluginExtension {
    def licenseHeaderText = 
         "/**\${VRL-LICENSE-HEADER-FILE-NAME}\n\n"+
         "* LICENSE HEADER TEMPLATE\n\n" + 
         "* Note: define license header with either\n" + 
         "*  'repairHeaders.licenseHeaderText = 'license text'\n" +
         "* or\n"+
         "*  'repairHeaders.licenseHeaderText = new File(projectDir,'license-template.txt)\n" +
         "*/\n";
}
