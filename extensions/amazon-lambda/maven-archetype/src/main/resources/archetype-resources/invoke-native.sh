aws lambda invoke --function-name my-native-function --payload file://payload.json out --log-type Tail --query 'LogResult' --output text |  base64 -d
