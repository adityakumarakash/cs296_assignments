package cs296MovieAnalysis;

import java.io.*;
import java.util.*;

/**
 * This class has functions which are static and are used in other classes in the package.
 */
class StringSupport
{
	/**
	 * The function adds the element of array b into vector a.
	 */
	public static void add(Vector<String> a, String[] b)	// to add elements from b to array a
	{
		for(String temp:b)
		{
			a.add(temp);
		}
	}
	
	/**
	 * The function prints the string array element.
	 */
	public static void print(String[] arr)			// to print the elemnts from aray s
	{
		for(String temp:arr)
		{
			System.out.println(temp);
		}
	}
	
	/**
	 * This function prints the elments of vector of string.
	 */
	public static void print(Vector<String> arr)			// to print the elemnts from vector s
	{
		for(String temp:arr)
		{
			System.out.println(temp);
		}
	}
	
	/**
	 * this function checks is a string is present in an array of strings
	 */
	public static boolean isPresent(String[] arr, String s)		// to check if a string is in an array
	{
		for(String temp:arr)
		{
			if(s.equals(temp))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This function checks if an integer is present in array of intgers
	 */
	public static boolean isPresent(int[] arr, int s)		// to check if a string is in an array
	{
		for(int temp:arr)
		{
			if(s==temp)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This function checks if an integer is present in a vector of integers.
	 */ 
	public static boolean isPresent(Vector<Integer> arr, int s)		// to check if a string is in an array
	{
		for(int temp:arr)
		{
			if(s==temp)
			{
				return true;	
			}
		}
		return false;
	}
	
	/**
	 * This function checks if two string arrays have anything in common
	 */
	public static boolean isCommon(String[] a, String[] b)		// to see if array a & b have common elements
	{
		for(String temp1:a)
		{
			for(String temp2:b)
			{
				if(temp1.equals(temp2))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * This function checks if a vector and array of string have something in common
	 */
	public static boolean isCommon(Vector<String> a, String[] b)		// to see if array a & b have common elements
	{
		for(String temp1:a)
		{
			for(String temp2:b)
			{
				if(temp1.equals(temp2))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * This fucntions counta the number of times a is in b
	 */
	public static int countWords(String a, String b)		// count of a in b
	{
		int pos=0, count=0;
		while(pos!=-1)
		{
			pos=b.indexOf(a, pos);
			if(pos!=-1)
			{
				pos+=1;
				count++;
			}
		}
		return count;
	}
	
	/**
	 * This function counts the number of words in s have prefix as prefix
	 */
	public static int countPrefix(String[] s, String prefix)  // count the occurence of this prefix in this array
	{
		int count=0;
		for(String temp:s)
		{
			if(temp.matches(prefix+".*"))
				count++;
		}
		return count;
	}
}


/**
 * This class contains the data needed by the various classes for the carrying out the various operations
 */
class ResourceSupport
{
	// this class contains the various arrays which are useful different operations
	static String[] prepositions={"ABOUT","ABOVE","ELSEWHERE","ELSE","ACROSS","AFTER","AGAINST","ALONG","AMONG","AROUND","AT","BEFORE","BEHIND","BELOW","BENEATH","BESIDE","BETWEEN","BEYOND","BUT","BY","DESPITE","DOWN","DURING","EXCEPT","FOR","FROM","IN","INSIDE","INTO","LIKE","NEAR","OF","OFF","ON","ONTO","OUT","OUTSIDE","OVER","PAST","SINCE","THROUGH","THROUGHOUT","TILL","TO","TOWARD","UNDER","UNDERNEATH","UNTIL","UP","UPON","WITH","WITHIN","WITHOUT"};
	static String[] conjunctions={"AND","THAT","BUT","STILL","LATER","OR","AS","IF","WHEN","THAN","BECAUSE","WHILE","WHERE","AFTER","SO","THOUGH","SINCE","UNTIL","WHETHER","BEFORE","ALTHOUGH","NOR","LIKE","ONCE","UNLESS","NOW","EXCEPT"};
	static String[] pronouns={"YOU","ALL","EVERYONE","EVERYBODY","IT","HIS","HE","SHE","HIM","HER","NEARBY"};
	static String[] script_words={"VOICE","RINGS","END","DIALOGUE","]","DETAIL","SHOOT","FADE","CONTINUED","CONTINUOUS","START","CREDITS","EXT.","A","A U","U"};
	static String[] months={"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
	static String[] days={"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","DAY"};
	static String[] day_time={"MORNING","NIGHT","AFTERNOON"};
	static String[] common_nouns={"HILLS","SOLDIERS","LOVERS","BATTLE","ARMY","DOGS","PEOPLE","CROWD","FOLKS","CHILDREN","OTHERS","SILENCE"};
	static String[] common_places={"ROOM","KITCHEN","CAMP","BATHROOM"};
	static String[] equipments={"TV","CAR","BOOTH"};
	static String[] inanimate={"BRIDGE","POV","UNIFORM","BUS","TRUCK","TRAIN","TELIVISION","LOUDSPEAKER","SHOTGUN	","CAR","UPSTAIRS","RIVER","ICE","MINUTES","HOURS","CORRIDOR","RESUME","CAMERA","BINOCULARS"};
	static String[] places={"LONDON"};
	static String[] spverbs={"READS","SERIOUSLY","WE","ASKS","ASK","COMPUTER","DONE","GETTING","YUPPIE","UNDERSTANDING","TORNADO","CITY","FACE","SAYS","DISCOVERING","BEGIN","BEGINS","No","INT.","EXT."};
	
	// list which can be said to be male or female straight away
	static String[] male_list={"waiter","uncle","male","man","soldier","boy","scout","father","grandfather","husband","brother","mr.","mr","prince","sergeant"};
	static String[] female_list={"waitress","aunt","nurse","female","girl","girls","lady","sister","wife","maid","mother","woman","grandmother","mrs","mrs.","princess","witch"};

	static String[] male_resolvers_back={"he","his","him","himself","he's","him.","himself.","his.","sir","sir.","brother"};
	static String[] female_resolvers_back={"she","her","herself","she's","her.","herself.","madam","maam","sister"};

	static String[] male_resolvers_forward={"mr","mr.","mister"};
	static String[] female_resolvers_forward={"mrs","mrs.","miss"};

	static String[] male_first_person_address={"boy","man","male","brother","father"};
	static String[] female_first_person_address={"girl","woman","female","sister","mother"};
	
	// the words helpful in finding the villain
	static String[] vill_words={"evil","death","pain","damage","lie","liar","lied","lethal","life for life","villain","kill you","weapon","detonator","murder","strike","negative","kill","fear","torture","hurt","harm","harmful","loot","rob","shoot","brutal","thrash","rape","destroy","burn","violence","vengeance","frighten"};
	static String[] good_words={"love","noble","perceive","song","sing","peace","hero","help","save","repect","great","honest","smile","beloved","forgive","forgives","kind","service","darling","loved","sweetheart","thank","thankyou","sorry","joy","promise"};
	
	//list to decide the genre
	//static String[] sci={"sci","robo","elec","hyper","lap","sub","comp","machi","mech","artificial","intel","radio","holo","death","dense","alch","atom","alter","barren","horror","imag","speed","human","time","light","sound","image","space","equip","security","chrono","dir","camera","signal","panel","massive","flash","mega","nano","mind","pos","psyc","star","super","stellar","terra","topo","univ","xeno","alien","extra","ufo","andr","beam","cyb","earth","et","dimension","flying","martian","tele","anim","adv","anti","bizzare","galax","grav","vacuum","tech","temp","clone","breach","expo","magn","magic","meteor","myth","prob","theor","under","voy","vio","adv","x-ray","auto","air","aero","acce"};
	static String[] romance={"bless","joy","dream","goddess","heart","throb","lov","ador","affec","allur","angel","attra","awe","insp","beaut","belov","brave","brigth","candid","capti","caring","charm","cheek","cheer","commit","compassion","comple","craft","creat","cult","curvy","curious","daring","darling","dazz","dedicat","deli","down-to-earth","easy","emotion","enchant","energ","engag","enig","entertain","excit","exquisite","feminine","forgiv","foxy","fun","friend","generous","gentl","genuin","gift","giv","glamor","good","gorg","grace","handsom","heaven","hot","hypno","innocent","intoxi","juice","kind","kiss","laid","leggy","loyal","magic","baby","doll","dear","fantasy","honey","partner","playmate","soul","myst","warm","trust","tender","sweet","strong","sex","sens","seduc","roman","prett","passion"};
	static String[] fantasy={"abnormal","abracadabra","adventure","alchemy","allegor","allusion","amulet","andersen","apparition","apprentice","beast","berserk","bizarr","bogey","brew","castle","cauldron","cave","chalice","changeling","charisma","charm","chimerical","clairvoyant","class","cliffs","cosmic","conjure","conspir","creat","crow","cruel","crystal","curious","curse","deform","demon","detect","disappear","disaster","dragon","dream","dwarf","eek","eerie","elf","empire","esp","evil","fab","ring","famil","fanci","fantas","fascina","fiction","fiery","figment","folk","foolish","force","forg","gestur","ghost","glimmer","gnome","goblin","godmother","gown","grate","graveyard","grimm","fairy","grotesque","hag","hallucinate","harbinger","hollow","horror","howl","idyll","illusion","imag","imp","incantation","incognit","ingenious","inspir","invisibl","jaunt","jiggl","legerdemain","leprechauns","lore","lucky","lunar","magic","majesty","mask","medieval","miracle","mischie","monster","moon","mus","myst","myth","nature","necro","nemesis","newt","oberon","ogre","oracle","overwhelm","owl","petrify","pixie","poison","potent","potion","powder","power","prey","princ","prophet","prowl","quail","quash,","quaver","queen","quest","raconteur","rage","realm","reign","reveal","robe","rule","sage","sandman","scare","scold","scroll","seek","seer","shaman","soothsayer","sorce","spect","specula","spell","spider","spirit","stars","super","talisman","terror","thrill","torch","tragic","tremors","troll","unbelievable","unexplained","unicorn","unusual","valiant","vampire","van","varie","venom","version","vic","vital","wail","wand","ward","weird","werewolf","whim","whine","whisk","whisper","white","wick","willies","win","wis","witch","worry","worship","wrinkl","yearn","yester","zap","zeal","zig"};
	static String[] horror={"abhor","adventure","affect","afraid","allure","alone","amulet","annihilate","antics","apparition","bat","beast","black","blood","bon","boo","broom","bubbl","cackle","captivate","arvings","cat","cauldron","caution","cemetery","chill","clairvoyant","cloak","cloudy","coffin","concoction","conjure","coven","crawl","creak","creep","cringe","crypt","curses","dank","dark","decapitat","dematerialize","demon","devil","disgust","dismal","divin","doom","dracula","dusk","eerie","enchant","entice","entomb","escapade","evil","fairy","fear","feast","fogg","fortune","foul","frankenstein","freak","fright","frozen","fury","gasp","ghast","ghost,","ghost","ghoul","gnarl","gnome","goose","flesh","grave","grim","groan","grotesque","gruesome","hackle","hag","halluc","haunt","haven","headless","hecat","hein","hex","hobble","hobgoblin","horri","horseman","hover","howl","hyster","imitate","imp","infect","influenc","jinx","keeper","labyr","lantern","luna","lycanthrope","macabre","maggot","magic","maniac","manifest","maraud","mask","maze","mesmerize","metamor","miasma","moan","moldy","monste","moon","mourn","mummy","mutation","myst","netherworld","newt","night","nois","nonsense","obscure","occult","ogre","outfit","parade","pendant","pentagram","petrify","phantasm","phantom","phenomenon","possess","potion","prank","premonition","prey","prowl","psych","quake","quest","quiver","rasp","rattle","repugnant","revolt","ritual","roar","rote","sarcophagus","scar","scream","screech","scythe","seer","shaman","shive","shock","shrew","shriek","sibyl","sinister","skeleton","skitter","skul","slink","soothsayer","sorcer","soul","spect","spell","spider","spine","spirit","stuff,","super","suspici","swarm","tactic","terror","toad","tomb","tortur","treasure","trick","troll","twilight","ugly","uneasy","unexpected","unleash","unmask","vampire","vixen","voodoo","wail","wander","war","wart","waylay","weird","whisper","whistle","wicked","wild","witch","wizard","wolf","worr","yells","yelp","zap","zombie","zone","zorro"};
	static String[] adventure={"luck","thrill","emprise","saga","cliff-hanger","expedien","hairraiser","hero","wage","roman","venture","gest","hazard","gamble","stake","avid","chance","enterprise","experience","odyssey","plunge","achieve","adventure","capitalist","affair","argo","beau","beryl","bainbridge","blood","breathtaking","capture","code","conte","dangerous","undertaking","deed","dime","entertain","episode","errant","exciting","exploit","explore","fly","forest","gad","grey","high","jeopar","knight","misadventur","picaresque","picaro","point","recital","return","rider","haggard","venture","romantic","risk","smollett","soldier","swash","take","tarzan","taste"};
	static String[] comedy={"comedy","fun","laugh","kidding","stupid","fool","dork","douchebag","shit","idiot","jok","craz","freak","jerk","scum","bimbo","weird","humor","chuckle","giggl","crack","joke","comed","happ","amuse","bitch","drunk","hangover","cool","sex","beer","crash","dude","idiot","confus","smile","dance","joy","gag","hilar","farce","mimic","cheek","flooz","hoi","loopy","bobo","rum","yahoo","booz","catch","chaos","widen","tease","fancy","strew","overturn","squeak","honey","toilet","disappear","annoy","screw","asshole","smile","pun","bar","strip","chuckle","title","howl","snigger","jeer","hah","pooh","pshaw","comic","lov","conviv","merry","lively"};
	static String[] sci={"sci","adventure","alchemy","align","alliance","alter","anima","anti","atmosphere","atom","auto","barren","bizarre","breach","chemical","civil","cliffhanger","clone","concept","conduct","creat","cyborgs","danger","develop","discover","drugs","earth","eerie","energy","episode","evidence","experiment","explor","explos","expos","fantasy","fireball","flame","force","function","fundament","futur","galaxy","glimps","gothic","gravit","halluci","holo","hypnosis","hypothesis","identif","illus","imagin","inhabit","interpret","invest","jargon","judg","jumble","lunar","magic","magnet","manuscript","marvel","meteor","micro","miraculous","monstrous","moon","myst","myth","nordic","novel","nuclear","oasis","observ","oracle","paralysis","particle","pestilence","physical","planet","potential","potion","prediction","prehistoric","preserve","pressure","probe","pulp","quantity","quest","react","real","recover","revolutio","robo","safety","security","sense","sound","space","speculat","speed","spirit","star","strange","synth","tech","temp","theor","time","token","travel","undercurrent","unexplainable","universe","unleash","unmanned","utopia","vacuum","victim","violence","virtual","voyage","wary","weird","wing","wisdom","world","x-ray","vision","zeal","zeitgeist"};
	static String[] drama={"drama","chorus","comedy","thespis","act","theatre","choral","choruses","event","incongruity","stage","direct","thespian","tragedy","moral","catastrophe","opera","buskin","catastasis","choragus","closet","drama","little","theater","miracle","sock","strophe","tragicomedy","antistrophe","catharsis","intermezzo","mystery","play","noh","performing","arts","satyr","wagner","atellan","choreodrama","chronicle","conservatory","corneille","corny","d'annunzio","defenders","dithyramb","performance","personae","galanty","show","hauptmann","hochhuth","kalidasa","monodrama","monodrame","passion","peripeteia","proverb","rostand","shadow","shadow","show","staging","street","swashbuckler","theater","absurd","theatr","product","tragic","character","melodrama","epitasis","kabuki","protasis","soubrette","characteriz","conflict","denouement","deuteragon","dialogue","entr'acte","irony","perform","protagon","stichomythia","theater","antagonist","bland","coryphaeus","deus","machina","dionysus","imbroglio","interlude","interpret","kathakali","legitimate"};
	static String[] action={"action","act","influence","process","conduct","event","effect","military","legal","operat","react","proceed","break","actuate","release","deed","passive","resist","decay","emergency","interact","synergy","war","capture","conflict","corros","cut","defense","desorp","engagem","inspir","motive","polic","repercus","replevin","absorp","agency","answer","antagon","concretion","counter","claim","eros","galvan","leach","perform","plan","practic","resist","scandal","soak","swing","trick","urgent","war","adduct","aggres","antagon","attack","battle","jump","run","blast","gun","bomb","save","protocol","law","hero","find","kill","danger","speed","sped","spy","drift","race","fbi","lapd","nypd","die","hard","thrown","throne","army","attack","devast","destroy"};


}
