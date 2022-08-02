package cn.dq.utils;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.apache.commons.codec.binary.Base64;


public class RsaConvert {
    public static String formatPkcs1ToPkcs8(String rawKey) throws Exception {
        String validKey = rawKey;
        byte[] encodeByte = Base64.decodeBase64(validKey);
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.pkcs8ShroudedKeyBag);
        ASN1Object asn1Object = ASN1ObjectIdentifier.fromByteArray(encodeByte);
        PrivateKeyInfo privKeyInfo = new PrivateKeyInfo(algorithmIdentifier, asn1Object);
        byte[] pkcs8Bytes = privKeyInfo.getEncoded();
        return Base64.encodeBase64String(pkcs8Bytes);
    }
}
