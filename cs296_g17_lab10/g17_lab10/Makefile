PROJECT_ROOT=.
SRC_DIR=$(PROJECT_ROOT)/src
JAVA_CLASS=$(PROJECT_ROOT)/javaClasses
DOC=$(PROJECT_ROOT)/doc

FILES := $(wildcard $(SRC_DIR)/*.java)

#FILTERED = $(filter-out $(SRC_DIR)/MovieAnalysisG17.java,$(FILES))

.PHONY : all doc clean

all : 
	@mkdir -p $(JAVA_CLASS)
	@echo "Made the folder"
	@javac -d $(JAVA_CLASS) $(FILES)
	@echo "compliled all the .java files"

run :
	@cd $(JAVA_CLASS);\
	java MovieAnalysisG17;
	
doc :
	@echo "generating.."
	@doxygen $(DOC)/Doxyfile
	@echo "done.."
	
clean :
	@echo "cleaning up..."
	@rm -rf $(JAVA_CLASS)
	@rm -rf $(DOC)/html
	@echo "done!"
