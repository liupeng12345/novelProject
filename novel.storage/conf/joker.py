import  urllib.request
import re
import  time
class Spider:
    def __init__(self):
        self.siwtch = True
        self.page = 1;
    def loadpage(self,page):
        url = "http://www.neihan8.com/article/list_5_" + str(page) + ".html"
        headers = {
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299"

        }
        request = urllib.request.Request(url = url, headers= headers)
        respons = urllib.request.urlopen(request)
        html =respons.read()
        print(html.decode("GBK"))
        patter = re.compile('<div\sclass="f18 mb20">(.*?)</div>', re.S)
        content_list = patter.findall(html.decode("GBK"))
        for i in content_list:
            print(i)
if __name__ == '__main__':
    spider = Spider()
    spider.loadpage(1)