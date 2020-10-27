package xyz.leuo.capes.shared.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.Data;
import org.bson.Document;
import xyz.leuo.capes.shared.capes.Cape;
import xyz.leuo.capes.shared.capes.CapeManager;
import xyz.leuo.capes.shared.profiles.Profile;

import javax.print.Doc;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
public class MongoManager {

    public static MongoManager instance;

    private MongoClientOptions.Builder options;
    private MongoClientURI mongoClientURI;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private @Getter MongoCollection<Document> capes, profiles;

    public MongoManager(String uri, String database) {
        instance = this;

        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);

        this.options = MongoClientOptions.builder();

        this.mongoClientURI = new MongoClientURI(uri);
        this.mongoClient = new MongoClient(mongoClientURI);
        this.mongoDatabase = mongoClient.getDatabase(database);

        List<String> collections = new ArrayList<>(Arrays.asList("capes", "profiles"));
        for(String s : this.mongoDatabase.listCollectionNames()) {
            collections.remove(s);
        }

        if(!collections.isEmpty()) {
            for(String s : collections) {
                this.mongoDatabase.createCollection(s);
            }
        }

        this.capes = this.mongoDatabase.getCollection("capes");
        this.profiles = this.mongoDatabase.getCollection("profiles");
    }

    public Profile pullProfile(UUID uuid) {
        Document found = this.getProfiles().find(new Document("uuid", uuid.toString())).first();
        if(found != null) {
            Profile profile = new Profile(UUID.fromString(found.getString("uuid")));

            List<Cape> capes = new ArrayList<>();
            for(String s : found.getList("ownedCapes", String.class)) {
                UUID u = UUID.fromString(s);
                Cape cape = CapeManager.instance.findCape(u);
                if(cape != null) {
                    capes.add(cape);
                } else {
                    cape = this.pullCape(u);
                    if(cape != null) {
                        capes.add(cape);
                    }
                }
            }

            UUID u = UUID.fromString(found.getString("appliedCape"));
            Cape cape = CapeManager.instance.findCape(u);
            if(cape == null) {
                cape = this.pullCape(u);
            }

            profile.setName(found.getString("name"));
            profile.setCustomCapeURL(found.getString("customCapeURL"));
            profile.setLastUpdate(found.getDate("lastUpdate"));
            profile.setCustomCape(found.getBoolean("customCape"));
            profile.setAppliedCape(cape);
            profile.setOwnedCapes(capes);

            return profile;
        } else {
            return null;
        }
    }

    public void pushProfile(Profile profile) {
        Document found = this.getProfiles().find(new Document("uuid", profile.getUuid().toString())).first();

        if(found != null) {
            this.profiles.deleteOne(found);
        }

        List<String> capes = new ArrayList<>();
        for(Cape cape : profile.getOwnedCapes()) {
            capes.add(cape.getUuid().toString());
        }

        Document document = new Document();
        document.put("uuid", profile.getUuid().toString());
        document.put("name", profile.getName());
        document.put("customCapeURL", profile.getCustomCapeURL());
        document.put("lastUpdate", profile.getLastUpdate());
        document.put("customCape", profile.isCustomCape());
        document.put("appliedCape", profile.getAppliedCape().getUuid().toString());
        document.put("ownedCapes", capes);

        this.profiles.insertOne(document);
    }

    public Cape pullCape(UUID uuid) {
        Document found = this.getCapes().find(new Document("uuid", uuid.toString())).first();
        if(found != null) {
            Cape cape = new Cape(uuid);

            cape.setName(found.getString("name"));
            cape.setDisplayName(found.getString("displayName"));
            cape.setUrl(found.getString("url"));

            return cape;
        } else {
            return null;
        }
    }

    public void pushCape(Cape cape) {
        Document found = this.getCapes().find(new Document("uuid", cape.getUuid().toString())).first();
        if(found != null) {
            this.getCapes().deleteOne(found);
        }

        Document document = new Document();
        document.put("uuid", cape.getUuid().toString());
        document.put("name", cape.getName());
        document.put("displayName", cape.getDisplayName());
        document.put("url", cape.getUrl());
    }
}
