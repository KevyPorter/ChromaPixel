 package net.kevyporter.chromapixel.commands;
 
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.net.URL;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.TimeZone;
 import java.util.regex.Pattern;
 import net.minecraft.command.CommandBase;
 import net.minecraft.command.ICommandSender;
 import net.minecraft.entity.player.EntityPlayerMP;
 import net.minecraft.server.MinecraftServer;
 import net.minecraft.server.management.ServerConfigurationManager;
 import net.minecraft.util.ChatComponentText;
 import net.minecraft.util.EnumChatFormatting;
 import org.apache.commons.lang3.StringUtils;
 
 public class NameViewCommand
   extends CommandBase
 {
		private boolean isOp(ICommandSender sender)
		{
			return (MinecraftServer.getServer().isSinglePlayer()) 
					|| (!(sender instanceof EntityPlayerMP))
					|| MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayerMP)sender).getGameProfile());
		}
   
   public String getCommandName()
   {
     return "names";
   }
   
   public int getRequiredPermissionLevel()
   {
     return 0;
   }
   
   public boolean canSenderUseCommand(ICommandSender sender)
   {
     return true;
   }
   
   public String getCommandUsage(ICommandSender sender)
   {
     return "/names <player>";
   }
   
   public void processCommand(ICommandSender sender, String[] args)
   {
	   try
	    {
	      if (args.length == 0)
	      {
	        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Usage: /names <playername>"));
	        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Example: /names KevyPorter"));
	      }
	      else if (args.length == 1)
	      {
	        String playername = args[0].toString();
	        if (playername.length() < 3)
	        {
	          sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Usage: /names <playername>"));
	          sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Example: /names KevyPorter"));
	        }
	        else
	        {
	          URL uuidgrabber = new URL("https://api.mojang.com/users/profiles/minecraft/" + playername);
	          BufferedReader br1 = new BufferedReader(new InputStreamReader(uuidgrabber.openStream()));
	          String uuidfromweb;
	          if ((uuidfromweb = br1.readLine()) != null)
	          {
	            String uuid = uuidfromweb.substring(7, 39);
	            int uuidL = uuid.length();
	            URL namegrabber = new URL("https://api.mojang.com/user/profiles/" + uuid + "/names");
	            BufferedReader br2 = new BufferedReader(new InputStreamReader(namegrabber.openStream()));
	            String webnames;
	            if ((webnames = br2.readLine()) != null)
	            {
	              if (webnames.substring(10, webnames.length() - 3 - playername.length()).length() <= 0)
	              {
	                sender.addChatMessage(new ChatComponentText(""));
	                sender.addChatMessage(new ChatComponentText(StringUtils.center("" + EnumChatFormatting.LIGHT_PURPLE + EnumChatFormatting.BOLD + playername, 65)));
	                sender.addChatMessage(new ChatComponentText(""));
	                sender.addChatMessage(new ChatComponentText(StringUtils.center(EnumChatFormatting.YELLOW + "Player has never changed their name.", 65)));
	                sender.addChatMessage(new ChatComponentText(""));
	              }
	              else
	              {
	                webnames = webnames.replace("{", "").replace("}", "").replaceAll(",", ".").replace('"', ' ').replace(" ", "").replace("[", "").replace("]", "").replace(".c", "-c").replace(".", ",");
	                String[] split = webnames.split(Pattern.quote(","));
	                
	                sender.addChatMessage(new ChatComponentText(""));
	                for (String s : split) {
	                  if ((s.startsWith("name")) && (s.contains("changed")))
	                  {
	                    String[] names = s.split(Pattern.quote("-"));
	                    String p1 = "";
	                    String p2 = "";
	                    for (String d : names)
	                    {
	                      if (d.startsWith("name")) {
	                        p1 = "    " + EnumChatFormatting.GOLD + "- " + EnumChatFormatting.GREEN + d.replace("name:", "") + " ";
	                      }
	                      if (d.startsWith("changedToAt"))
	                      {
	                        long unixSeconds = Long.parseLong(d.replace("changedToAt:", ""));
	                        Date date = new Date(unixSeconds);
	                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	                        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
	                        String formattedDate = sdf.format(date);
	                        p2 = EnumChatFormatting.GRAY + "(Changed on " + formattedDate + ")";
	                      }
	                    }
	                    sender.addChatMessage(new ChatComponentText("" + p1 + p2));
	                  }
	                  else if ((s.startsWith("name")) && (!s.contains("changed")))
	                  {
	                    sender.addChatMessage(new ChatComponentText(StringUtils.center("" + EnumChatFormatting.LIGHT_PURPLE + EnumChatFormatting.BOLD + s.replace("name:", ""), 65)));
	                    sender.addChatMessage(new ChatComponentText(""));
	                  }
	                }
	                sender.addChatMessage(new ChatComponentText(""));
	              }
	            }
	            else
	            {
	              sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "ERROR: Could not find player '" + playername + "'."));
	              sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "This person changed their name or never existed."));
	            }
	            br2.close();
	          }
	          else
	          {
	            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "ERROR: Could not find player '" + playername + "'."));
	            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "This person changed their name or never existed."));
	          }
	          br1.close();
	        }
	      }
	      else if (args.length > 1)
	      {
	        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Usage: /names <playername>"));
	        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Example: /names KevyPorter"));
	      }
	    }
	    catch (Throwable e)
	    {
	      e.printStackTrace();
	    }
   }
 }


