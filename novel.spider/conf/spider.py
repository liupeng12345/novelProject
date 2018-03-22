import urllib.request
import sys
headers = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299"}
def u(url):
    request = urllib.request.Request(url=url,headers=headers)
    data = urllib.request.urlopen(request).read()
    try:
        data = data.decode('UTF-8')
    except(UnicodeDecodeError):
        data=data.decode("GBK")
    print(data)
if __name__ == '__main__':
    u(sys.argv[1])