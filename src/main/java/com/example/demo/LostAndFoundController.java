package com.example.demo;
/**
 * Created by Adil Imam on 7/1/2017.
 */
/*
 If its a fucking maven project, please place your config files in under resources folder. Lesson Learned
*/
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;

@CrossOrigin(origins = "http://54.91.15.90", maxAge = 3600)
@RestController
public class LostAndFoundController {
   //This is going to be the best application
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private ConnectJDBC JDBCconnect=new ConnectJDBC();
    private Statement stmt = null;
    private Connection conn=null;
    private PreparedStatement preparedStatement=null;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/retrievelost")
    public List<LostItem> test(){
        List<LostItem> lostReports= new ArrayList<>();
        try{
            conn=ConnectJDBC.getConnection();
            stmt = conn.createStatement();
            //  String sql = "SELECT * FROM Lost_Items LIMIT "+startIndex+", 10";
            String sql = "SELECT * FROM Lost_Items";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("id");
                String name= rs.getString("name");
                String description = rs.getString("description");
                String lost_location = rs.getString("lost_location");
                double reward_price=rs.getDouble("reward_price");
                String picture=rs.getString("picture");
                String owner_name=rs.getString("owner_name");
                String owner_phone=rs.getString("owner_phone");

                LostItem lost=new LostItem(name, description, lost_location, reward_price,
                        picture, owner_name, owner_phone);
                lost.setId(id);
                lostReports.add(lost);
            }
            rs.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }
            catch(SQLException se){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return lostReports;
    }


    @RequestMapping("/retrievefound")
    public List<FoundItem> getFoundItems(){
        List<FoundItem> foundReports= new ArrayList<>();
        try{
            conn=ConnectJDBC.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Found_Items NATURAL JOIN Lost_Items";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt("id");
                String finderName= rs.getString("finder_name");
                String finderPhone = rs.getString("finder_phone");
                String finderEmail = rs.getString("finder_email");
                String picture=rs.getString("picture");
                String itemName=rs.getString("name");
                String lostLocation=rs.getString("lost_location");

                FoundItem found=new FoundItem(id, finderName, finderPhone, finderEmail, itemName, lostLocation, picture);
                foundReports.add(found);
            }
            rs.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }

        for(FoundItem found:foundReports){
            System.out.println("Final death "+found);
        }
        return foundReports;
    }


    @RequestMapping(value="/lostdata", method=RequestMethod.POST)
    public Greeting lost(@RequestBody Map<String, String> json){

        Collection<String> values=json.values();
        Object[] valuesArray=values.toArray();

        LostItem lost=new LostItem(valuesArray[0].toString(),valuesArray[1].toString(),
                valuesArray[2].toString(),Double.parseDouble(valuesArray[3].toString()),valuesArray[4].toString(),
                valuesArray[5].toString(),valuesArray[6].toString());

        try{
            conn=ConnectJDBC.getConnection();
            String query = "INSERT INTO Lost_Items(name, description, lost_location, reward_price, picture, owner_name, owner_phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement=conn.prepareStatement(query);
            preparedStatement.setString(1, lost.getName());
            preparedStatement.setString(2, lost.getDescription());
            preparedStatement.setString(3, lost.getLost_location());
            preparedStatement.setDouble(4, lost.getReward_price());
            preparedStatement.setString(5, lost.getPicture());
            preparedStatement.setString(6,lost.getOwner_name());
            preparedStatement.setString(7,lost.getOwner_phone());
            preparedStatement.execute();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return new Greeting(counter.incrementAndGet(),
               "");
    }

    @RequestMapping(value="/founddata", method=RequestMethod.POST)
    public Greeting found(@RequestBody Map<String, Object> json){

        final JSONObject obj = new JSONObject(json);
        final JSONArray items= obj.getJSONArray("items");
        final int limit=items.length();

        for(int i=0;i<limit;i++){
               int id=items.getInt(i);
            try{
                conn=ConnectJDBC.getConnection();
                String query = "INSERT INTO Found_Items(id, finder_name, finder_phone, finder_email) VALUES (?, ?, ?, ?)";
                preparedStatement=conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, obj.getString("owner_name"));
                preparedStatement.setString(3, obj.getString("owner_phone"));
                preparedStatement.setString(4, obj.getString("owner_email"));

                preparedStatement.execute();
                conn.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
       }
        return new Greeting(counter.incrementAndGet(),
                "");
    }
/*
    @RequestMapping(value="/upload", method=RequestMethod.POST)  //new annotation since 4.3
    public Greeting singleFileUpload(@RequestBody MultipartFile file){
        //Upload the file to s3

        if (!file.isEmpty()) {
            try {
                imagePath=UploadS3.uploadImageToS3(file);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return new Greeting(counter.incrementAndGet(),imagePath);
    }
*/
}