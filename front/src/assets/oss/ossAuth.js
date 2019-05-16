const OSS = require('ali-oss');


let client = new OSS({
    region: 'oss-cn-hangzhou',
    //云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，部署在服务端使用RAM子账号或STS，部署在客户端使用STS。
    accessKeyId: 'LTAIJ9mYIjuW54Cj',
    accessKeySecret: '89EMlXLsP13H8mWKIvdr4iM1OvdVxs',
    bucket: 'course-lg'
});

export default {
    client,
}
