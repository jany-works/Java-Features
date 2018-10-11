var feed='https://www.safeworkaustralia.gov.au/rss/news';
var url=new java.net.URL(feed);
var input=new java.util.Scanner(url.openStream());
input.useDelimiter('$');//Mostly char $ won't be there
var contents = input.next();
contents;