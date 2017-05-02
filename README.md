## pkmn-battle-sim
Installation (for FXGL)

-Pull but don't open the project yet until you install maven

# 1. Installing Maven (build mananger / to import fxgl)
  - Open Eclipse
  - Go to Help -> Install New Software...
  - input 'http://download.eclipse.org/technology/m2e/releases' in the Work with: textbox
  - Hit enter
  - Check on Maven Integration with for Eclipse
  - finish installation (eclipse will restart after installation)
  
# 2. Loading FXGL
  - Right click on project in eclipse, click on maven->Update->Okay
  - It should run and compile
  - WIP (should be automatic after you install the maven plugin)
  
# Troubleshooting
  - Close the project and delete it (not from disk)
  - File->Import->Maven->Existing Maven Projects->(navigate to the project folder)->Finish
  - Go to step 2
