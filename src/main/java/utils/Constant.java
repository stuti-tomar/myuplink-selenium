package utils;

 

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

 

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {

 

    // Project paths
    public static final String RESOURCES_FOLDER_PATH = System.getProperty("user.dir") + "/src/test/resources"; 

    // Test data paths
    public static final String CONS_TEST_DATA = RESOURCES_FOLDER_PATH + "/testdata/MyUplinkTestData.xlsx";
    public static final String url="https://stageweb.nibejpi.com/";
    
    

 

}