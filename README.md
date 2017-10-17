# DisposableEmailAPI
API for trashcanmail.com

# How to use
It's fairly simple. 
```
EmailAddress mycoolemail = new EmailAddress("mycoolemail");
String acoolemail = mycoolemail.getBySubject("Hello, world").get(0).getContent();
System.out.println(acoolemail);
```
All emails are @trashcanmail.com. In the example above, the address to send emails to would be mycoolemail@trashcanmail.com. When declaring an EmailAddress object, do not have "@trashcanmail.com" in the input.

# Dependencies
[Jsoup](jsoup.org)
