To Build for usage

0. Make sure to provide the RTC Plain Java Client Libraries as a user library named PlainJavaAPI.
0. com.ibm.js.team.monitoring.custom.service.expensivescenario is required to build this.
1. Open the project com.ibm.js.team.monitoring.custom.expensivescenario in Eclipse. 
3. Right click the project com.ibm.js.team.monitoring.custom.expensivescenario
4. Select Export
5. Select Java>Runnable Jar File 
6. In the wizard 
   - For 'Launch Configuration' select 'ExpensiveScenarioNotifier - Runnable Jar'
   - In 'Export Location' select " a path e.g. 'C:\temp\ESN\esn.jar' 
     You can change the root for the export if needed but keep the top folder name 
     TSM and don't modify the name of the JAR file; 
   - In 'Library handling' select 
      1. 'Package required libraries into generated JAR' or
      2. 'Copy required libraries into a sub folder next to the generated JAR'
   - In the last section you can choose to save the export as an ANT script.
   - Click Finish and allow to create the folder.
7. Copy the content of the projects sub-folder scripts into the export location 
   folder 'C:\temp\ESN\'. The files copied are script files, tsm.bat and tsm.sh, 
   the license file LICENSE.html and the log configuration file log4j.properties
8. Check the script files and provide a dedicated JRE 1.8 if needed
9. On Unix make the script file esn.sh you just copied executable
10. If, in step 6, you picked 2. 'Copy required libraries into a sub folder next to the generated JAR',
    delete the folder 'C:\temp\ESN\esn_lib', if you want to control which PlainJava Client Libraries folder 
    to use in the esn.bat/esn.sh launch script. You can provide the Plain Java Client Libraries folder or use 
    the exported library files 

The application is now usable. 

To ship it 
1. Select the folder 'C:\temp\ESN\' and compress the file
2. Rename the archive file to esn-Vx-YYYYMMDD.zip, 
   where x is the version, YYYY is the year, MM is the month and DD is the day
3. The file is now ready for shipping. It can basically just be uncompressed 
   on a different machine in some folder and used from there.
  