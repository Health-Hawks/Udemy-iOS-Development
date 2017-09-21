var europeanCapitals: [String] = []
var asianCapitals: [String] = []
var otherCapitals: [String] = []

let world = [
    "BEL": "Brussels",
    "LIE": "Vaduz",
    "BGR": "Sofia",
    "USA": "Washington D.C.",
    "MEX": "Mexico City",
    "BRA": "Brasilia",
    "IND": "New Delhi",
    "VNM": "Hanoi"]


for (key,value) in world {
    switch key {
    case "BEL","LIE","BGR": europeanCapitals.append(value)
    case "VNM","IND": asianCapitals.append(value)
    default: otherCapitals.append(value)
    }
}

print(europeanCapitals)
print(asianCapitals)
print(otherCapitals)


enum mobilePhone{
    case iPhone (String)
    case android (String)
    case windowsPhone (String)
}

let iphone = mobilePhone.iPhone("7 Plus")
print(iphone)
